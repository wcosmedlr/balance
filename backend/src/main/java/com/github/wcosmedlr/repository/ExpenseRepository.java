package com.github.wcosmedlr.repository;

import com.github.wcosmedlr.dao.Expense;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    List<Expense> listOrderByTimeStampDesc();

}
