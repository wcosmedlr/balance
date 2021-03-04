package com.github.wcosmedlr.adapters.secondary.persistence.mappers;

import com.github.wcosmedlr.adapters.secondary.persistence.models.MemberEntity;
import com.github.wcosmedlr.domain.models.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper extends MapperBase<Member, MemberEntity>{

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

}
