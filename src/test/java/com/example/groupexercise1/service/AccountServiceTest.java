package com.example.groupexercise1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
	@DisplayName("Should save regular account and return correct details")
	public void shouldSaveRegularAccountAndReturnDetails() {
		String accountName = "Juan Dela Cruz";
		
		Account expectedResponse = new RegularAccount();
		expectedResponse.setName("Juan Dela Cruz");
		expectedResponse.setAcctNumber("123456");
		expectedResponse.setMinimumBalance(500d);
		
		when(accountRepository.save(any()))
			.thenReturn(expectedResponse);
	    
	    AccountDto actualResponse = regAcctService.createAccount(accountName);
	    
	    verify(accountRepository).save(any());
	    
	    assertEquals(expectedResponse.getName(), actualResponse.getName());
	    assertEquals(expectedResponse.getAcctNumber(), actualResponse.getAcctNumber());
	    assertEquals(expectedResponse.getMinimumBalance(), actualResponse.getMinimumBalance());
	    assertEquals(expectedResponse.getType(), actualResponse.getType());
	}

}
