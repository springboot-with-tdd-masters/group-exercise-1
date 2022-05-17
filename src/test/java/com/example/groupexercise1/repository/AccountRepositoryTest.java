package com.example.groupexercise1.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.groupexercise1.model.RegularAccount;


@DataJpaTest
public class AccountRepositoryTest {
	
	 @Autowired
	 private AccountRepository accountRepository;
	
	 @Test
	 @DisplayName("Should save RegularAccount Entity with the correct details")
	 public void saveRegularAccount() {
	     RegularAccount newAccount = new RegularAccount();
	     newAccount.setMinimumBalance(500d);
	     
	     RegularAccount actualResponse = accountRepository.save(newAccount);
	     
	     assertThat(actualResponse.getMinimumBalance()).isEqualTo(newAccount.getMinimumBalance());
	 }	
}
