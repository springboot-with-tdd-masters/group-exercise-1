/**
 * 
 */
package com.masters.masters.exercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masters.masters.exercise.model.Account;
import com.masters.masters.exercise.repository.AccountRepository;

/**
 * @author michaeldelacruz
 *
 */

@Service
public class TransactionImpl {

	@Autowired
	private AccountRepository repo;
	
	public Account withdraw(Account account, double amount) {
		double updatedBalance = account.getBalance() - amount;
		account.setBalance(updatedBalance);
		return repo.save(account);
	}
	
	public Account deposit(Account account, double amount) {
		Double updatedBalance = account.getBalance() + amount;
        account.setBalance(updatedBalance);
        return repo.save(account);
	}
	
	
}
