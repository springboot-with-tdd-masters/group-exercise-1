package com.group3.exercise.bankapp.adapters;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.exceptions.AccountTransactionException;
import com.group3.exercise.bankapp.request.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public class AccountAdapter {

    public TransactionResponse mapToResponse(Account account){
        try {
            TransactionResponse response = new TransactionResponse();
            response.setBalance(account.getBalance());
            response.setInterestCharge(account.getInterestCharge());
            response.setMinimumBalance(account.getMinimumBalance());
            response.setAcctNumber(account.getAcctNumber());
            response.setName(account.getName());
            response.setTransactionCharge(account.getTransactionCharge());
            response.setId(account.getId());
            response.setPenalty(account.getPenalty());
            return response;
        } catch (Exception e){
            throw new AccountTransactionException("Unable to map response");
        }
    }

}
