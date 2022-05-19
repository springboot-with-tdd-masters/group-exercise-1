package com.group3.exercise.bankapp.controllers;

import com.group3.exercise.bankapp.request.CreateAccountRequest;
import com.group3.exercise.bankapp.response.AccountResponse;
import com.group3.exercise.bankapp.services.account.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public AccountResponse registerAccount(@RequestBody CreateAccountRequest request){
        return service.register(request);
    }
}
