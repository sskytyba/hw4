package com.example.hw4.repository;

import com.example.hw4.model.HistoryField;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Component
public class HistoricalFieldRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisCacheManager redisCacheManager;
    private final BeanPropertyRowMapper<HistoryField> beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(HistoryField.class);

    public List<HistoryField> findMany(Integer fieldId, Integer domainId, Integer userId) {
        return namedParameterJdbcTemplate.query("select * from historical_fields where user_id=:userId and domain_id=:domainId and field_id=:fieldId",
                                                new MapSqlParameterSource(Map.of("userId", userId,
                                                                                 "domainId", domainId,
                                                                                 "fieldId", fieldId)),
                                                beanPropertyRowMapper);
    }

    public List<HistoryField> findManyCached(Integer fieldId, Integer domainId, Integer userId) {
        String keyName = String.format("SimpleKey [%d,%d,%d]", fieldId, domainId, userId);
        Cache cache = redisCacheManager.getCache("itemCache");
        List<HistoryField> v = cache.get(keyName, List.class);
        if (v == null) {
            // lock on java side for simplicity
            synchronized (keyName.intern()) {
                v = cache.get(keyName, List.class);
                if (v != null) {
                    return v;
                }
                v = findMany(fieldId, domainId, userId);
                cache.put(keyName, v);
            }
        }
        return v;
    }

    private final AtomicLong deltaMillis = new AtomicLong(0);
    public List<HistoryField> findManyProbability(Integer fieldId, Integer domainId, Integer userId) {
        Cache cache = redisCacheManager.getCache("itemCache");
        double beta = 0.5;
        String keyName = String.format("itemCache::SimpleKey [%d,%d,%d]", fieldId, domainId, userId);
        Long millisToExpire = Optional.ofNullable(redisTemplate.getExpire(keyName, TimeUnit.MILLISECONDS)).orElse(0L);
        List<HistoryField> historyField = cache.get(keyName.replace("itemCache::", ""), List.class);
        double probabilityValue = -1 * deltaMillis.get() * beta * Math.log(Math.random());
        if (historyField == null || probabilityValue >= millisToExpire) {
            // lock on java side fore simplicity
            synchronized (keyName.intern()) {
                List<HistoryField> newValue = cache.get(keyName.replace("itemCache::", ""), List.class);
                if ((historyField == null && newValue != null) ||
                        (historyField != null && redisTemplate.getExpire(keyName, TimeUnit.MILLISECONDS) >= millisToExpire)) {
                    return newValue;
                }
                System.out.println(keyName);
                long millis = System.currentTimeMillis();
                newValue = findMany(fieldId, domainId, userId);
                deltaMillis.set(System.currentTimeMillis() - millis);

                cache.put(keyName.replace("itemCache::", ""), newValue);

                return newValue;
            }
        }

        return historyField;
    }

}
