package com.advancejava.groupexercise1.adaptor;

import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.entity.CheckingAccount;
import com.advancejava.groupexercise1.entity.InterestAccount;
import com.advancejava.groupexercise1.entity.RegularAccount;
import com.advancejava.groupexercise1.helper.RandomNumberGeneratorUtility;
import com.advancejava.groupexercise1.model.AccountRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestToAccountAdaptorImpl implements AccountRequestToAccountAdaptor{

    public Account convert(AccountRequest accountRequest) {
        Account account = buildAccount(accountRequest);
        account.setAcctNumber(RandomNumberGeneratorUtility.generate());
        return account;
    }

    private Account buildAccount(AccountRequest accountRequest) {
        Account account;
        String name = accountRequest.getName();

        switch (accountRequest.getType()) {
            case "regular":
            account = new RegularAccount(name);
            break;

            case "interest":
                account = new InterestAccount(name);
                break;

            default:
                account = new CheckingAccount(name);
                break;

        }

        return account;
    }
}
