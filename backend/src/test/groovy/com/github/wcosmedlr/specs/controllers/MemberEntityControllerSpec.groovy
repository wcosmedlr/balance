package com.github.wcosmedlr.specs.controllers


import com.github.wcosmedlr.clients.MemberClient
import com.github.wcosmedlr.dto.Expense
import com.github.wcosmedlr.dto.Member
import com.github.wcosmedlr.mothers.MemberMother
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MemberEntityControllerSpec extends Specification {

    @Inject
    @Shared
    @AutoCleanup
    private EmbeddedApplication<?> application

    private MemberClient client = application.applicationContext.getBean(MemberClient)

    @Inject
    @Shared
    private MemberMother memberMother

    /*
    When the user sends a request the members
    Then the system must return all members
*/
    void 'get all the members'() {
        when:
        Single<List<Expense>> result = client.findAll();

        then:
        List<Expense> expensesObtained = result.blockingGet()
        expensesObtained.toSet() == memberMother.createAListOfMembersToCheckBalances().toSet()
    }

     /*
        Given an valid member
        When the user sends a request to add it
        Then the system must add the member
    */
    void 'add a member'() {
        given:
        Member member = memberMother.createValidUser1()
        when:
        final Maybe<Long> result = client.add(member);
        Long id = result.blockingGet();
        id != null

        and:
        Member memberObtained = client.findById(id).blockingGet();

        then:
        member == memberObtained;
    }
}
