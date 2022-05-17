package com.advancejava.groupexercise1.service;

import com.advancejava.groupexercise1.adaptor.AccountRequestToAccountAdaptor;
import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.model.AccountRequest;
import com.advancejava.groupexercise1.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRequestToAccountAdaptor accountRequestToAccountAdaptor;

    @Override
    public Account save(AccountRequest accountRequest) {

        Account account = accountRequestToAccountAdaptor.convert(accountRequest);

        return accountRepository.save(account);
    }
}
