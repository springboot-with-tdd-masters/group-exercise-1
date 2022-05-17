package com.example.groupexercise1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.groupexercise1.model.dto.AccountDto;
import com.example.groupexercise1.model.dto.AccountRequestDto;
import com.example.groupexercise1.service.AccountService;

@RequestMapping("/accounts") 
@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	
	@PostMapping
	public AccountDto createAccount(@RequestBody AccountRequestDto account) {
		return accountService.createAccount(account);
	}
	
	@GetMapping
	public AccountDto getAllAccounts() {
		return null;
	}
	
}
