package com.github.wcosmedlr.helpers.mothers

import com.github.wcosmedlr.domain.models.BalanceMember

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BalanceMother<T extends BalanceMember>{

    final private  MemberMother memberMother

    @Inject
    BalanceMother(MemberMother memberMother) {
        this.memberMother = memberMother
    }

    T createValidBalance1(){
        return BalanceMember.builder()
                .setOwner(memberMother.createValidUser1())
                .setValue(37.5D)
                .build()
    }

    T createValidBalance2(){
        return BalanceMember.builder()
                .setOwner(memberMother.createValidUser2())
                .setValue(12.5D)
                .build()
    }

    T createValidBalance3(){
        return BalanceMember.builder()
                .setOwner(memberMother.createValidUser3())
                .setValue(-12.5D)
                .build()
    }

    T createValidBalance4(){
        return BalanceMember.builder()
                .setOwner(memberMother.createValidUser4())
                .setValue(-37.5D)
                .build()
    }

    List<T> createAListOfBalancesToEvaluateExpenses() {
        T[] balances = new BalanceMember[] {
                createValidBalance1(),
                createValidBalance2(),
                createValidBalance3(),
                createValidBalance4(),
        };
        return Arrays.asList(balances);
    }

}
