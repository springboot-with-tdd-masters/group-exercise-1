package com.softvision.bank.tdd.services;

import com.softvision.bank.tdd.model.Account;

import java.util.List;

public interface BankAccountsService {

	public Account get(long id);

	public List<Account> get();

	public void deleteById(long id);

	public Account createUpdate(Account account);
}
