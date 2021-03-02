package com.github.wcosmedlr.domain.models

import com.github.wcosmedlr.helpers.mothers.BalanceMother
import com.github.wcosmedlr.helpers.mothers.ExpenseMother
import com.github.wcosmedlr.helpers.mothers.MemberMother
import com.github.wcosmedlr.helpers.mothers.TransactionMother
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class BalanceSpec extends Specification{

    @Inject
    private MemberMother memberMother

    @Inject
    private ExpenseMother expenseMother

    @Inject
    private BalanceMother balanceMother

    @Inject
    private TransactionMother transactionMother

    /*
        Given a set of members and transactions
        When we request for calculate balances
        Then the system must return the current balance of each member
    */
    void 'get balances'() {
        given:
        var members = memberMother.createAListOfMembersToCheckBalances()
        var expenses = expenseMother.createAListOfExpensesToCheckBalances()

        when:
        var balanceMembers = Balance.builder()
                .setMembers(members)
                .setExpenses(expenses)
                .build()
                .calculateBalanceStatus()
                .getBalanceMembers()

        then:
        balanceMembers == balanceMother.createAListOfBalancesToEvaluateExpenses()
    }

    /*
        Given a balance status
        When we request for calculate transactions
        Then the system must return the current balance of each member
    */
    void 'get transactions'() {
        given:
        var members = memberMother.createAListOfMembersToCheckBalances()
        var expenses = expenseMother.createAListOfExpensesToCheckBalances()
        var balanceStatus = Balance.builder()
                .setMembers(members)
                .setExpenses(expenses)
                .build()
                .calculateBalanceStatus()

        when:
        var transactions = balanceStatus.calculateTransactions()

        then:
        transactions == transactionMother.createAListOfTransactionsToEvaluateBalances()
    }

}
