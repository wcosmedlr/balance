package com.github.wcosmedlr.adapters.secondary.persistence.mappers;

import org.mapstruct.MappingTarget;

public interface MapperBase <T, K>{

    void toDAO(T DBO, @MappingTarget K DAO);
    T toDBO(K DAO);

}
