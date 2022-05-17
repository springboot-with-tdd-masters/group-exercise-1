package com.example.groupexercise1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.groupexercise1.model.Account;
import com.example.groupexercise1.model.RegularAccount;
import com.example.groupexercise1.model.dto.AccountDto;
import com.example.groupexercise1.model.dto.AccountRequestDto;
import com.example.groupexercise1.repository.AccountRepository;
import com.example.groupexercise1.util.AccountGenerator;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountRequestDto accountRequest) {
		if (accountRequest.getType().equals("regular")) {
			Account newAccount = new RegularAccount();
			newAccount.setName(accountRequest.getName());
			newAccount.setMinimumBalance(500d);
			newAccount.setBalance(newAccount.getMinimumBalance());
			newAccount.setPenalty(10d);
			newAccount.setTransactionCharge(0d);
			newAccount.setInterestCharge(0d);
			newAccount.setAcctNumber(AccountGenerator.generateAccountNumber());
			
			Account savedAccount = accountRepository.save(newAccount);
			return new AccountDto(savedAccount);
		} else {
			throw new IllegalArgumentException("Account type not supported");
		}
	}

	@Override
	public AccountDto getAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		return accountRepository
			.findAll()
			.stream()
			.map(a -> new AccountDto(a))
			.collect(Collectors.toList());		
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
