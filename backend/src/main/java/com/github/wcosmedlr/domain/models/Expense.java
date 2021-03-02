package com.github.wcosmedlr.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;
import java.util.Date;

@Data
@SuperBuilder(setterPrefix = "set", toBuilder = true)
@NoArgsConstructor
public class Expense {

    private Long id;
    private Member owner;
    private Double value;
    private String description;
    private Date timeStamp;

    public static final Comparator<Expense> compareByTimeStamp = Comparator.comparing(Expense::getTimeStamp);

}
