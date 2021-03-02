package com.github.wcosmedlr.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set", toBuilder = true)
@NoArgsConstructor
public class BalanceMember implements Comparable<BalanceMember>{

    private Member owner;
    private Double value;

    @Override
    public int compareTo(BalanceMember o) {
        return this.value.compareTo(o.getValue());
    }

}
