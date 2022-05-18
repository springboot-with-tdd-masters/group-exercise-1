package com.group3.exercise.bankapp.adapters;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.exceptions.AccountTransactionException;
import com.group3.exercise.bankapp.response.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountAdapter {

    public AccountResponse mapToResponse(Account account){
        try {
            AccountResponse response = new AccountResponse();
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
            throw new AccountTransactionException(HttpStatus.BAD_REQUEST, "Unable to map response");
        }
    }

}
