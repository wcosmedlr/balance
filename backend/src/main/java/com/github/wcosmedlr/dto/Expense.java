package com.github.wcosmedlr.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;
import java.util.Date;

@Data
@SuperBuilder(setterPrefix = "set")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Expense extends MoneyUnit {

    private Long id;
    private String description;
    private Date timeStamp;

    public static final Comparator<Expense> compareByTimeStamp = Comparator.comparing(Expense::getTimeStamp);

}
