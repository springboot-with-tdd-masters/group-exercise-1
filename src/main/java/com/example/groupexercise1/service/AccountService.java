package com.example.groupexercise1.service;

import java.util.List;

import com.example.groupexercise1.model.Account;
import com.example.groupexercise1.model.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(Account account);
	AccountDto getAccount(Long accountId);
	List<AccountDto> getAllAccounts();
	AccountDto createTransaction(String type, long accountId, double amount);
	void deleteAccount(long accountId);
}
