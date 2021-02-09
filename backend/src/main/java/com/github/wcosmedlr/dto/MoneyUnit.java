package com.github.wcosmedlr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set")
@NoArgsConstructor
public class MoneyUnit implements Comparable<MoneyUnit> {

    private Member owner;
    private Double value;

    @Override
    public int compareTo(MoneyUnit o) {
        return this.getValue().compareTo(o.getValue());
    }

}
