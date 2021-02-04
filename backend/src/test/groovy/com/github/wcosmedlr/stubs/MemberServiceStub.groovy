package com.github.wcosmedlr.stubs


import com.github.wcosmedlr.models.Member
import com.github.wcosmedlr.mothers.MemberMother
import com.github.wcosmedlr.services.MemberServiceI
import io.micronaut.context.annotation.Primary
import io.reactivex.Maybe
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Primary
class MemberServiceStub implements MemberServiceI {

    private MemberMother memberMother;

    @Inject
    MemberServiceStub(MemberMother memberMother) {
        this.memberMother = memberMother
    }

    @Override
    Maybe<Long> add(Member member) {
        if (memberMother.createValidUser1() == member) {
            return Maybe.just(0)
        } else {
            return Maybe.empty()
        }
    }

    @Override
    Maybe<Member> findById(Long id) {
        if (id == 0) {
            return Maybe.just(memberMother.createValidUser1())
        } else {
            return Maybe.empty()
        }
    }

    @Override
    Single<List<Member>> findAll() {
        return Single.just(memberMother.createAListOfMembersToCheckBalances());
    }
}
