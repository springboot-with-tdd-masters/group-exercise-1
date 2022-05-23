package com.group3.exercise.bankapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group3.exercise.bankapp.request.CreateAccountRequest;
import com.group3.exercise.bankapp.response.AccountResponse;
import com.group3.exercise.bankapp.services.account.AccountService;

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
    
    @GetMapping()
    public List<AccountResponse> getAllAccounts() {
    	return service.getAllAccounts();
    }
    
    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable("id") Long id) {
    	return service.getAccountById(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable("id") Long id) {
    	service.deleteAccountById(id);
    	return ResponseEntity.noContent().build();
    }
}
