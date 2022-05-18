package com.softvision.bank.tdd.services;

import com.softvision.bank.tdd.model.Account;
import com.softvision.bank.tdd.model.CheckingAccount;
import com.softvision.bank.tdd.model.InterestAccount;
import com.softvision.bank.tdd.exceptions.RecordNotFoundException;
import com.softvision.bank.tdd.model.RegularAccount;
import com.softvision.bank.tdd.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BankAccountsServiceImpl implements BankAccountsService {

	@Autowired
	AccountRepository repository;

	@Override
	public Account get(long id) {
		return repository.findById(id).orElseThrow(RecordNotFoundException::new);
	}

	@Override
	public List<Account> get() {
		return repository.findAll();
	}

	@Override
	public void deleteById(long id) {
		repository.findById(id).orElseThrow(RecordNotFoundException::new);
		repository.deleteById(id);
	}

	@Override
	public Account createUpdate(Account account) {
		account.setAcctNumber(generateRandomAcctNum());
		if (account instanceof RegularAccount) {
			account.setPenalty(10);
			account.setMinimumBalance(500);
			account.setBalance(500);
		} else if (account instanceof CheckingAccount) {
			account.setMinimumBalance(100);
			account.setTransactionCharge(1);
			account.setPenalty(10);
			account.setBalance(100);
		} else if (account instanceof InterestAccount) {
			account.setInterestCharge(0.03);
		}

		account = repository.save(account);
		return account;
	}

	private static String generateRandomAcctNum() {
		return new Random().ints(12, 48, 57).mapToObj(c -> Character.toString((char) c)).collect(Collectors.joining());
	}
}
