package com.masters.masters.exercise.services;

import com.masters.masters.exercise.exception.RecordNotFoundException;
import com.masters.masters.exercise.model.Account;
import com.masters.masters.exercise.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountRepository repo;

    // create
    public Account createOrUpdateAccount(Account entity) throws RecordNotFoundException {
        Optional<Account> account = repo.findById(entity.getId());
        if (account.isPresent()) {
            Account newEntity = account.get();
            newEntity.setName(entity.getName());
            newEntity.setBalance(entity.getBalance());
            newEntity = repo.save(newEntity);
            return newEntity;
        } else {
            entity = repo.save(entity);
            return entity;
        }
    }

    // get account by id
    // get account by acctNumber
    //get all accounts
    public List<Account> getAllAccounts() {
        List<Account> accountList = repo.findAll();
        if (accountList.size() > 0) {
            return accountList;
        } else {
            return new ArrayList<Account>();
        }
    }
    //withdraw/deposit
    //delete
}
