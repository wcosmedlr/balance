package com.github.wcosmedlr.adapters.primary.controllers.models;

import com.github.wcosmedlr.domain.models.BalanceMember;
import com.github.wcosmedlr.domain.models.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(setterPrefix = "set")
@NoArgsConstructor
public class BalanceControllerModel {
    List<BalanceMember> balanceMembers;
    List<Transaction> transactions;
}
