package com.github.wcosmedlr.specs.controllers

import com.github.wcosmedlr.clients.TransactionClient
import com.github.wcosmedlr.dto.Transaction
import com.github.wcosmedlr.mothers.TransactionMother
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TransactionControllerSpec extends Specification{

    @Inject
    @Shared
    @AutoCleanup
    private EmbeddedApplication<?> application

    private TransactionClient client = application.applicationContext.getBean(TransactionClient)

    @Inject
    @Shared
    private TransactionMother transactionMother;

    /*
        When the user sends a request to know how to set the group balance
        Then the system must suggest movements to get a balanced status
    */
    void 'movements required to balance the system'() {
        when:
        Single<List<Transaction>> result = client.findAll()

        then:
        List<Transaction> transactions = result.blockingGet();
        transactions == transactionMother.createAListOfTransactionsToEvaluateBalances()
    }
}
