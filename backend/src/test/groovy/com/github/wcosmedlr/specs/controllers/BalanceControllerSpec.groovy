package com.github.wcosmedlr.specs.controllers

import com.github.wcosmedlr.clients.BalanceClient
import com.github.wcosmedlr.dto.Balance
import com.github.wcosmedlr.mothers.BalanceMother
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class BalanceControllerSpec extends Specification{

    @Inject
    @Shared
    @AutoCleanup
    private EmbeddedApplication<?> application

    private BalanceClient client = application.applicationContext.getBean(BalanceClient)

    @Inject
    @Shared
    private BalanceMother balanceMother;

    /*
      When the user sends a request of the balance
      Then the system must return the current balance of each member
    */
    void 'get all member balances'() {
        when:
        Single<List<Balance>> result = client.findAll()

        then:
        List<Balance> balances = result.blockingGet();
        balances == balanceMother.createAListOfBalancesToEvaluateExpenses()
    }
}
