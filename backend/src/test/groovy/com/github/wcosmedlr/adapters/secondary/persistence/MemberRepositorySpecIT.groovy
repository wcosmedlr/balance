package com.github.wcosmedlr.adapters.secondary.persistence

import com.github.wcosmedlr.domain.models.Expense
import com.github.wcosmedlr.domain.models.Member
import com.github.wcosmedlr.domain.services.MemberService
import com.github.wcosmedlr.helpers.mothers.MemberMother
import com.github.wcosmedlr.helpers.specs.IntegrationBase
import io.reactivex.Maybe
import io.reactivex.Single
import spock.lang.Shared

import javax.inject.Inject

class MemberRepositorySpecIT extends IntegrationBase{

    private MemberService memberService = getBean(MemberService)

    @Inject
    @Shared
    private MemberMother memberMother;

    /*
        When the user sends a request the members
        Then the system must return all members
    */
    void 'get all the members'() {
        given:
        List<Expense> members = memberMother.createAListOfMembersToCheckBalances()
        members.forEach(memberService::add)

        when:
        Single<List<Expense>> result = memberService.findAll()

        then:
        List<Expense> expensesObtained = result.blockingGet()
        expensesObtained.toSet() == members.toSet()
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
        final Maybe<Long> result = memberService.add(member);
        Long id = result.blockingGet();
        id != null

        and:
        member.setId(id)
        Member memberObtained = memberService.findById(id).blockingGet();

        then:
        member == memberObtained;
    }

}
