package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dao.MemberEntity;
import com.github.wcosmedlr.dto.Member;
import com.github.wcosmedlr.repository.MemberRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MemberService implements MemberServiceI {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Inject
    public MemberService(ModelMapper modelMapper, MemberRepository memberRepository) {
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
    }

    @Override
    public Maybe<Long> add(Member member) {
        MemberEntity entity = modelMapper.map(member, MemberEntity.class);
        entity = memberRepository.save(entity);
        return Maybe.just(entity.getId());
    }

    @Override
    public Maybe<Member> findById(Long id) {
        MemberEntity entity = memberRepository.findById(id).orElse(null);
        return Maybe.just(modelMapper.map(entity, Member.class));
    }

    @Override
    public Single<List<Member>> findAll() {
        List<com.github.wcosmedlr.dto.Member> result = StreamSupport.stream(Spliterators.spliteratorUnknownSize(memberRepository.findAll().iterator(), Spliterator.ORDERED), false)
                .map(expenseEntity -> modelMapper.map(expenseEntity, Member.class))
                .collect(Collectors.toList());
        return Single.just(result);
    }
}
