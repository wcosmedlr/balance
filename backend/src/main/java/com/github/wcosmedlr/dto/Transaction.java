package com.github.wcosmedlr.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Transaction extends MoneyUnit {

    private Member benefactor;

}
