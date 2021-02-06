package com.github.wcosmedlr.services;

import com.github.wcosmedlr.dto.Transaction;
import io.reactivex.Single;

import java.util.List;

public interface TransactionServiceI {
    Single<List<Transaction>> findAll();
}
