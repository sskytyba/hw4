package com.example.hw4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryField implements Serializable {
    public Integer fieldId;
    public Integer domainId;
    public Integer userId;
    public LocalDate date;
}
