package com.example.hw4.controller;

import com.example.hw4.model.HistoryField;
import com.example.hw4.repository.HistoricalFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HistoryFieldController {

    private final HistoricalFieldRepository historicalFieldRepository;

    @GetMapping("/get")
    public List<HistoryField> get(@RequestParam("fieldId") Integer fieldId,
                               @RequestParam("domainId") Integer domainId,
                               @RequestParam("userId") Integer userId) {
        return historicalFieldRepository.findMany(fieldId, domainId, userId);
    }

    @GetMapping("/getCached")
    public List<HistoryField> getCached(@RequestParam("fieldId") Integer fieldId,
                                        @RequestParam("domainId") Integer domainId,
                                        @RequestParam("userId") Integer userId)  {
        return historicalFieldRepository.findManyCached(fieldId, domainId, userId);
    }

    @GetMapping("/getCachedProbability")
    public List<HistoryField> getCachedProbability(@RequestParam("fieldId") Integer fieldId,
                                                   @RequestParam("domainId") Integer domainId,
                                                   @RequestParam("userId") Integer userId) {
        return historicalFieldRepository.findManyProbability(fieldId, domainId, userId);
    }


}
