package com.example.groupexercise1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.groupexercise1.model.Account;
import com.example.groupexercise1.model.RegularAccount;
import com.example.groupexercise1.model.dto.AccountDto;
import com.example.groupexercise1.repository.AccountRepository;

public class RegularAccountServiceTest {
	
	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private RegularAccountService regAcctService;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	@DisplayName("Should save Book entity and return correct details")
	public void shouldSaveBookAndReturnDetails() {
		Account requestAcct = new RegularAccount();
		requestAcct.setAcctNumber("123456");
		requestAcct.setAmount(5000d);
		
		Account expectedResponse = new RegularAccount();
		expectedResponse.setAcctNumber("123456");
		expectedResponse.setAmount(5000d);
		
		when(accountRepository.save(requestAcct))
			.thenReturn(expectedResponse);
	    
	    AccountDto actualResponse = regAcctService.createAccount(requestAcct);
	    
	    verify(accountRepository)
	    	.save(requestAcct);
	    assertEquals(expectedResponse.getAcctNumber(), actualResponse.getAcctNumber());
	    assertEquals(expectedResponse.getAmount(), actualResponse.getAmount());
	}

}
