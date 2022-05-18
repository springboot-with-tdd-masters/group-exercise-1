/**
 * 
 */
package com.masters.masters.exercise.services;

import com.masters.masters.exercise.model.CheckingAccount;
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
		double updatedBalance;
		if(account instanceof  CheckingAccount){
			if(account.getBalance() < account.getMinimumBalance()){
				updatedBalance = account.getBalance() - account.getPenalty() - account.getTransactionCharge() - amount;
			}else{
				updatedBalance = account.getBalance() -  account.getTransactionCharge() - amount;
			}
		}else{
			updatedBalance = account.getBalance() - amount;
		}

		account.setBalance(updatedBalance);
		return repo.save(account);
	}

	public Account deposit(Account account, double amount) {
		Double updatedBalance;
		if(account instanceof CheckingAccount){
			updatedBalance  = account.getBalance() + amount - account.getTransactionCharge();
		}else{
			updatedBalance = account.getBalance() + amount;
		}

        account.setBalance(updatedBalance);
        return repo.save(account);
	}
}
