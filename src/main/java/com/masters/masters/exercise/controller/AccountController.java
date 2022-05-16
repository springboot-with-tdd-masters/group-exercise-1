package com.masters.masters.exercise.controller;

import com.masters.masters.exercise.exception.RecordNotFoundException;
import com.masters.masters.exercise.model.Account;
import com.masters.masters.exercise.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    //get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> list = accountService.getAllAccounts();
        return new ResponseEntity<List<Account>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<Account> createOrUpdateAccount(@RequestBody Account account) throws RecordNotFoundException {
        Account updated = accountService.createOrUpdateAccount(account);
        return new ResponseEntity<Account>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    // get account by id
    // get account by acctNumber
    //withdraw/deposit
    //delete
}
