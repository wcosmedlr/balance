package com.github.wcosmedlr.ports.primary.services;

import com.github.wcosmedlr.domain.models.BalanceStatus;
import io.reactivex.Single;

public interface BalanceServiceI {

    Single<BalanceStatus> findAllBalanceStatus();

}
