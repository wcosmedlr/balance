package com.github.wcosmedlr.adapters.secondary.persistence.persistence;

import com.github.wcosmedlr.adapters.secondary.persistence.persistence.models.MemberEntity;
import com.github.wcosmedlr.adapters.secondary.persistence.persistence.repositories.MemberRepository;
import com.github.wcosmedlr.domain.models.Member;
import com.github.wcosmedlr.ports.secondary.repositories.MemberRepositoryI;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class MemberRepositoryAdapter implements MemberRepositoryI {

    private final MemberRepository memberRepository;

    @Inject
    public MemberRepositoryAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Maybe<Long> add(Member member) {
        MemberEntity entity = new MemberEntity(member);
        entity = memberRepository.save(entity);
        return Maybe.just(entity.getId());
    }

    @Override
    public Maybe<Member> findById(Long id) {
        MemberEntity entity = memberRepository.findById(id).orElse(null);
        return Maybe.just(entity.toServiceModel());
    }

    @Override
    public Single<List<Member>> findAll() {
        List<Member> result = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(memberRepository.findAll().iterator(),
                        Spliterator.ORDERED), false)
                .map(entity -> entity.toServiceModel())
                .collect(Collectors.toList());
        return Single.just(result);
    }
}
