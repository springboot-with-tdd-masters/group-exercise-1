package com.masters.masters.exercise.controller;

import com.masters.masters.exercise.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    // create
    // get account by id
    // get account by acctNumber
    //get all accounts
    //withdraw/deposit
    //delete
}
