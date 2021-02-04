package com.github.wcosmedlr.mothers


import com.github.wcosmedlr.models.Balance
import com.github.wcosmedlr.models.Transaction

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionMother<T extends Transaction>{

    private MemberMother memberMother


    @Inject
    TransactionMother(MemberMother memberMother) {
        this.memberMother = memberMother
    }

    T createValidTransaction1(){
        return new Transaction.Builder()
                .setBenefactor(memberMother.createValidUser1())
                .setOwner(memberMother.createValidUser4())
                .setValue(37.5)
                .build()
    }

    T createValidTransaction2(){
        return new Transaction.Builder()
                .setBenefactor(memberMother.createValidUser2())
                .setOwner(memberMother.createValidUser3())
                .setValue(12.5)
                .build()
    }

    List<T> createAListOfTransactionsToEvaluateBalances() {
        T[] balances = new Balance[] {
                createValidTransaction1(),
                createValidTransaction2()
        };
        return Arrays.asList(balances);
    }
}
