package com.advancejava.groupexercise1.service;

import com.advancejava.groupexercise1.entity.Account;

import java.util.List;

public interface BankService {

    public Account getAccount(Integer id);
    public List<Account> getAccounts();
    public Account createAccount(Account acct);
    public Account updateAccount(Account acct);
}
