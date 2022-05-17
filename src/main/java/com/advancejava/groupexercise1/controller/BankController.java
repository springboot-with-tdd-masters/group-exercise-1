package com.advancejava.groupexercise1.controller;

import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.service.BankService;
import com.advancejava.groupexercise1.service.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account acct){
        return new ResponseEntity<>(bankService.createAccount(acct), HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Integer id){ return bankService.getAccount(id); }

    @GetMapping("/accounts")
    public List<Account> getAccount(){ return bankService.getAccounts(); }

    @PutMapping("/accounts")
    public Account updateAccount(Account acct){ return bankService.updateAccount(acct); }

}
