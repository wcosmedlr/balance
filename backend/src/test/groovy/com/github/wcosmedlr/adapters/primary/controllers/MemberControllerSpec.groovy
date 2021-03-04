package com.github.wcosmedlr.adapters.primary.controllers

import com.github.wcosmedlr.domain.models.Member
import com.github.wcosmedlr.helpers.clients.MemberClient
import com.github.wcosmedlr.helpers.mothers.MemberMother
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MemberControllerSpec extends Specification {

    @Inject
    private MemberClient client

    @Inject
    private MemberMother memberMother

    /*
    When the user sends a request the members
    Then the system must return all members
*/
    void 'get all the members'() {
        when:
        Single<List<Member>> result = client.findAll();

        then:
        List<Member> membersObtained = result.blockingGet()
        membersObtained.toSet() == memberMother.createAListOfMembersToCheckBalances().toSet()
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
