package com.softvision.bank.tdd.services;

import com.softvision.bank.tdd.exceptions.BadRequestException;
import com.softvision.bank.tdd.exceptions.InsufficientFundsAvailable;
import com.softvision.bank.tdd.exceptions.RecordNotFoundException;
import com.softvision.bank.tdd.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softvision.bank.tdd.repository.AccountRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static java.util.Optional.ofNullable;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	AccountRepository accountRepository;

	public Account transact(long id, Transaction transaction) {
		Account account = accountRepository.findById(id).orElseThrow(RecordNotFoundException::new);
		
		if(transaction.getAmount() <= 0)
			throw new BadRequestException();
		
		switch (ofNullable(transaction).map(Transaction::getType).map(String::toUpperCase)
				.orElseThrow(BadRequestException::new)) {
		case "DEPOSIT":
			account.setBalance(Double.sum(account.getBalance(), transaction.getAmount()));
			break;
		case "WITHDRAW":
			if (transaction.getAmount() > account.getBalance()) {
				throw new InsufficientFundsAvailable();
			}
			double diff = account.getBalance() - transaction.getAmount();
			account.setBalance(diff);
			break;
		default:
			throw new BadRequestException();
		}
		computeCharges(account);
		return accountRepository.save(account);
	}

	private static void computeCharges(Account account) {
		if (account instanceof RegularAccount) {
			computePenalties(account);
		} else if (account instanceof InterestAccount) {
			computeInterestCharge(account);
		} else if (account instanceof CheckingAccount) {
			computePenalties(account);
			computeTransactionCharge(account);
		}
	}

	private static void computePenalties(Account account) {
		if (account.getBalance() < account.getMinimumBalance()) {
			account.setBalance(account.getBalance() - account.getPenalty());
		}
	}

	private static void computeTransactionCharge(Account account) {
		account.setBalance(account.getBalance() - account.getTransactionCharge());
	}

	private static void computeInterestCharge(Account account) {
		LocalDate lastDayOfTheMonth = LocalDate
				.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				.with(TemporalAdjusters.lastDayOfMonth());
		if (lastDayOfTheMonth.getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
			double interest = (account.getBalance() * 0.03);
			account.setBalance(account.getBalance() + interest);
		}
	}
}
