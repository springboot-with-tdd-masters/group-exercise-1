package com.advancejava.groupexercise1.controller;

import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.model.AccountRequest;
import com.advancejava.groupexercise1.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @PostMapping()
    public ResponseEntity<Account> save(@RequestBody AccountRequest accountRequest) {
        LOGGER.info("Saving Account...");
        return new ResponseEntity<>(accountService.save(accountRequest), HttpStatus.OK);

    }
}
