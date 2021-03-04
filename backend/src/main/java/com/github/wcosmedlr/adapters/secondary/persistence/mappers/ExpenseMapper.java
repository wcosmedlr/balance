package com.github.wcosmedlr.adapters.secondary.persistence.mappers;

import com.github.wcosmedlr.adapters.secondary.persistence.models.ExpenseEntity;
import com.github.wcosmedlr.domain.models.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseMapper extends MapperBase<Expense, ExpenseEntity>{

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

}
