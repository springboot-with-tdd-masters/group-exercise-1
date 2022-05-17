package com.advancejava.groupexercise1.controller;

import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.service.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BankController {

    @Autowired
    private BankServiceImpl bankService;

    @PostMapping("/accounts")
    public List<Account> createAccount(@RequestBody Account acct){
        bankService.createAccount(acct);
        return bankService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Integer id){ return bankService.getAccount(id); }

    @GetMapping("/accounts")
    public List<Account> getAccount(){ return bankService.getAccounts(); }

    @PutMapping("/accounts")
    public Account updateAccount(Account acct){ return bankService.updateAccount(acct); }

}
