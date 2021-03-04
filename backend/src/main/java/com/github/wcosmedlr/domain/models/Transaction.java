package com.github.wcosmedlr.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set", toBuilder = true)
@NoArgsConstructor
public class Transaction {

    private Member owner;
    private Double value;
    private Member benefactor;

}
