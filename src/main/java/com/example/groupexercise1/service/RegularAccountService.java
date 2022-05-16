package com.example.groupexercise1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.groupexercise1.model.Account;
import com.example.groupexercise1.model.dto.AccountDto;
import com.example.groupexercise1.repository.AccountRepository;

@Service
public class RegularAccountService implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(Account account) {
		Account newAccount = accountRepository.save(account);
		return new AccountDto(newAccount);
	}

	@Override
	public AccountDto getAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto createTransaction(String type, long accountId, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(long accountId) {
		// TODO Auto-generated method stub
		
	}

}
