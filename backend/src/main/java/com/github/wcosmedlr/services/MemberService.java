package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dao.Member;
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
    public Maybe<Long> add(com.github.wcosmedlr.models.Member member) {
        Member entity = modelMapper.map(member, Member.class);
        entity = memberRepository.save(entity);
        return Maybe.just(entity.getId());
    }

    @Override
    public Maybe<com.github.wcosmedlr.models.Member> findById(Long id) {
        Member entity = memberRepository.findById(id).orElse(null);
        return Maybe.just(modelMapper.map(entity, com.github.wcosmedlr.models.Member.class));
    }

    @Override
    public Single<List<com.github.wcosmedlr.models.Member>> findAll() {
        List<com.github.wcosmedlr.models.Member> result = StreamSupport.stream(Spliterators.spliteratorUnknownSize(memberRepository.findAll().iterator(), Spliterator.ORDERED), false)
                .map(expenseEntity -> modelMapper.map(expenseEntity, com.github.wcosmedlr.models.Member.class))
                .collect(Collectors.toList());
        return Single.just(result);
    }
}
