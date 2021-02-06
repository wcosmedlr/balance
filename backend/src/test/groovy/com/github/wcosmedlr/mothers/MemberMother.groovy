package com.github.wcosmedlr.mothers

import com.github.wcosmedlr.dto.Member

import javax.inject.Singleton

@Singleton
class MemberMother <T extends Member>{

    T createValidUser1() {
        return Member.builder()
                .setName("Francisco")
                .setSurname("Buyo")
                .build();
    }

    T createValidUser2() {
        return Member.builder()
                .setName("Alfonso")
                .setSurname("Pérez")
                .build();
    }

    T createValidUser3() {
        return Member.builder()
                .setName("Raúl")
                .setSurname("González")
                .build();
    }

    T createValidUser4() {
        return Member.builder()
                .setName("Jose")
                .setSurname("María")
                .build();
    }

    List<T> createAListOfMembersToCheckBalances(){
        T[] members = new Member[] {
                createValidUser1(),
                createValidUser2(),
                createValidUser3(),
                createValidUser4(),
        };
        return Arrays.asList(members);
    }
}
