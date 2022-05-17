package com.advancejava.groupexercise1.adaptor;

import com.advancejava.groupexercise1.entity.Account;
import com.advancejava.groupexercise1.model.AccountRequest;

public interface AccountRequestToAccountAdaptor {

    public Account convert(AccountRequest accountRequest);
}
