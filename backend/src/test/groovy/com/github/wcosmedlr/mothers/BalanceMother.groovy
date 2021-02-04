package com.github.wcosmedlr.mothers


import com.github.wcosmedlr.models.Balance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BalanceMother<T extends Balance>{

    private MemberMother memberMother


    @Inject
    BalanceMother(MemberMother memberMother) {
        this.memberMother = memberMother
    }

    T createValidBalance1(){
        return new Balance.Builder()
                .setOwner(memberMother.createValidUser1())
                .setValue(37.5)
                .build()
    }

    T createValidBalance2(){
        return new Balance.Builder()
                .setOwner(memberMother.createValidUser2())
                .setValue(12.5)
                .build()
    }

    T createValidBalance3(){
        return new Balance.Builder()
                .setOwner(memberMother.createValidUser3())
                .setValue(-12.5)
                .build()
    }

    T createValidBalance4(){
        return new Balance.Builder()
                .setOwner(memberMother.createValidUser4())
                .setValue(-37.5)
                .build()
    }

    List<T> createAListOfBalancesToEvaluateExpenses() {
        T[] balances = new Balance[] {
                createValidBalance1(),
                createValidBalance2(),
                createValidBalance3(),
                createValidBalance4(),
        };
        return Arrays.asList(balances);
    }
}
