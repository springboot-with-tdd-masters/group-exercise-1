package com.advancejava.groupexercise1.service;

import com.advancejava.groupexercise1.adaptor.AccountRequestToAccountAdaptor;
import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.model.AccountRequest;
import com.advancejava.groupexercise1.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountRequestToAccountAdaptor accountRequestToAccountAdaptor;

    @InjectMocks
    private AccountService accountService = new AccountServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        //setup
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setName("John Doe");
        accountRequest.setType("regular");

        Account account = mock(Account.class);

        when(accountRequestToAccountAdaptor.convert(accountRequest))
                .thenReturn(account);

        //execute
        accountService.save(accountRequest);

        //test
        verify(accountRequestToAccountAdaptor)
                .convert(accountRequest);

        verify(accountRepository)
                .save(account);
    }
}
