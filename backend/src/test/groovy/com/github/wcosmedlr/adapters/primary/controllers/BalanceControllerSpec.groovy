package com.github.wcosmedlr.adapters.primary.controllers

import com.github.wcosmedlr.domain.models.Transaction
import com.github.wcosmedlr.helpers.clients.BalanceClient
import com.github.wcosmedlr.helpers.mothers.BalanceMother
import com.github.wcosmedlr.helpers.mothers.TransactionMother
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class BalanceControllerSpec extends Specification{

    @Inject
    private BalanceClient client

    @Inject
    private BalanceMother balanceMother

    @Inject
    private TransactionMother transactionMother

    /*
        When the user sends a request of the balance
        Then the system must return the current balance of each member
    */
    void 'get all member balances'() {
        when:
        var balances = client.findAll().blockingGet().getBalanceMembers()

        then:
        balances == balanceMother.createAListOfBalancesToEvaluateExpenses()
    }

    /*
        When the user sends a request to know how to set the group balance
        Then the system must suggest movements to get a balanced status
    */
    void 'get transactions'() {
        when:
        List<Transaction> transactions = client.findAll()
                .blockingGet()
                .getTransactions()

        then:
        transactions == transactionMother.createAListOfTransactionsToEvaluateBalances()
    }


}
