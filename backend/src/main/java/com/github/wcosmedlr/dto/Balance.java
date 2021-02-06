package com.github.wcosmedlr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set")
@NoArgsConstructor
public class Balance extends MoneyUnit {
}
