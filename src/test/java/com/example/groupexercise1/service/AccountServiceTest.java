package com.example.groupexercise1.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.groupexercise1.exeption.AccountNotFoundException;
import com.example.groupexercise1.model.Account;
import com.example.groupexercise1.model.CheckingAccount;
import com.example.groupexercise1.model.InterestAccount;
import com.example.groupexercise1.model.RegularAccount;
import com.example.groupexercise1.model.dto.AccountDto;
import com.example.groupexercise1.model.dto.AccountRequestDto;
import com.example.groupexercise1.repository.AccountRepository;

public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountServiceImpl accountService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should save regular account and return correct details")
	public void shouldSaveRegularAccountAndReturnDetails() {
		AccountRequestDto accountRequest = new AccountRequestDto();
		accountRequest.setName("Juan Dela Cruz");
		accountRequest.setType("regular");

		Account expectedResponse = new RegularAccount();
		expectedResponse.setName("Juan Dela Cruz");
		expectedResponse.setAcctNumber("123456");
		expectedResponse.setMinimumBalance(500d);
		expectedResponse.setBalance(expectedResponse.getMinimumBalance());
		expectedResponse.setPenalty(10d);
		expectedResponse.setTransactionCharge(0d);
		expectedResponse.setInterestCharge(0d);
		expectedResponse.setAcctNumber("12345678");

		when(accountRepository.save(any())).thenReturn(expectedResponse);

		AccountDto actualResponse = accountService.createAccount(accountRequest);

		verify(accountRepository).save(any());

		assertEquals(expectedResponse.getName(), actualResponse.getName());
		assertEquals(expectedResponse.getAcctNumber(), actualResponse.getAcctNumber());
		assertEquals(expectedResponse.getMinimumBalance(), actualResponse.getMinimumBalance());
		assertEquals(expectedResponse.getType(), actualResponse.getType());
	}

	@Test
	@DisplayName("Should return all accounts with correct details")
	public void shouldReturnAllAccountsWithCorrectDetails() {
		RegularAccount regularAccount = new RegularAccount();
		regularAccount.setName("Juan Dela Cruz");
		regularAccount.setMinimumBalance(500d);

		CheckingAccount checkingAccount = new CheckingAccount();
		checkingAccount.setName("Juan Dela Cruz I");
		checkingAccount.setMinimumBalance(100d);

		InterestAccount interestAccount = new InterestAccount();
		interestAccount.setName("Juan Dela Cruz II");
		interestAccount.setMinimumBalance(0d);

		List<Account> accounts = Arrays.asList(regularAccount, checkingAccount, interestAccount);

		when(accountRepository.findAll()).thenReturn(accounts);

		List<AccountDto> accountDtos = accountService.getAllAccounts();

		// expected returned objects
		AccountDto regularAcctDto = new AccountDto();
		regularAcctDto.setType("regular");
		regularAcctDto.setName("Juan Dela Cruz");
		regularAcctDto.setMinimumBalance(500d);

		AccountDto checkingAcctDto = new AccountDto();
		checkingAcctDto.setType("checking");
		checkingAcctDto.setName("Juan Dela Cruz I");
		checkingAcctDto.setMinimumBalance(100d);

		AccountDto interestAcctDto = new AccountDto();
		interestAcctDto.setType("interest");
		interestAcctDto.setName("Juan Dela Cruz II");
		interestAcctDto.setMinimumBalance(0d);

		verify(accountRepository).findAll();

		assertThat(accountDtos).hasSize(3);
		assertThat(accountDtos).containsExactlyInAnyOrder(regularAcctDto, checkingAcctDto, interestAcctDto);
	}

	@Test
	@DisplayName("Should delete account")
	public void shouldDeleteAccount_Test() {
		RegularAccount regularAccount = new RegularAccount();
		regularAccount.setName("Juan Dela Cruz");
		regularAccount.setMinimumBalance(500d);

		when(accountRepository.save(regularAccount)).thenReturn(regularAccount);

		accountRepository.delete(regularAccount);

		verify(accountRepository, times(1)).delete(regularAccount);
	}

	@Test
	@DisplayName("Delete account should return exception error")
	public void deleteAccount_ShouldReturnError_Test() {
		Optional<Account> regularAccount = Optional.empty();

		when(accountRepository.findById(0L)).thenReturn(regularAccount);
		assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccount(0L));
	}

}
