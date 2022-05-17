package com.advancejava.groupexercise1.service;

import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.model.AccountRequest;

public interface AccountService {

    public Account save(AccountRequest accountRequest);
}
