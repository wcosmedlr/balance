package com.github.wcosmedlr.domain.services;

import com.github.wcosmedlr.domain.models.Member;
import com.github.wcosmedlr.ports.primary.services.MemberServiceI;
import com.github.wcosmedlr.ports.secondary.repositories.MemberRepositoryI;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MemberService implements MemberServiceI {

    private final MemberRepositoryI memberRepository;

    @Inject
    public MemberService(MemberRepositoryI memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Maybe<Long> add(Member member) {
        return memberRepository.add(member);
    }

    @Override
    public Maybe<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Single<List<Member>> findAll() {
        return memberRepository.findAll();
    }
}
