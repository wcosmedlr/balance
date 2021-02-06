package com.github.wcosmedlr.mothers

import com.github.wcosmedlr.dto.Balance
import com.github.wcosmedlr.dto.Transaction

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionMother<T extends Transaction>{

    final private  MemberMother memberMother

    @Inject
    TransactionMother(MemberMother memberMother) {
        this.memberMother = memberMother
    }

    T createValidTransaction1(){
        return Transaction.builder()
                .setBenefactor(memberMother.createValidUser1())
                .setOwner(memberMother.createValidUser4())
                .setValue(37.5)
                .build()
    }

    T createValidTransaction2(){
        return Transaction.builder()
                .setBenefactor(memberMother.createValidUser2())
                .setOwner(memberMother.createValidUser3())
                .setValue(12.5)
                .build()
    }

    List<T> createAListOfTransactionsToEvaluateBalances() {
        T[] balances = new Transaction[] {
                createValidTransaction1(),
                createValidTransaction2()
        };
        return Arrays.asList(balances);
    }
}
