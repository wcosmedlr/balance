package com.github.wcosmedlr.domain.models;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder(setterPrefix = "set", toBuilder = true)
public class Balance {

    @NonNull
    private List<Member> members;
    @NonNull
    private List<Expense> expenses;

    public BalanceStatus calculateBalanceStatus(){
        final List<BalanceMember> totalSpentByMember = new ArrayList<>();
        final List<BalanceMember> balances = new ArrayList<>();

        members.stream().forEach(m ->
                totalSpentByMember.add(
                        BalanceMember.builder()
                                .setOwner(m)
                                .setValue(expenses.stream()
                                        .filter(e -> e.getOwner().equals(m))
                                        .map(Expense::getValue)
                                        .reduce(0D, Double::sum))
                                .build()
                )
        );

        final Double totalCost = totalSpentByMember.stream()
                .map(BalanceMember::getValue)
                .reduce(0D, Double::sum);
        final Double equalCost = totalCost / members.size();

        totalSpentByMember.stream().forEach(b ->
                balances.add(
                        BalanceMember.builder()
                                .setOwner(b.getOwner())
                                .setValue(b.getValue() - equalCost)
                                .build()
                )
        );

        return BalanceStatus.builder()
                .setBalanceMembers(balances)
                .build();
    }

}
