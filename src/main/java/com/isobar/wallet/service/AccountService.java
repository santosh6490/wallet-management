package com.isobar.wallet.service;

import com.isobar.wallet.entity.Account;

public interface AccountService {

  Long createAccount(Account account);

  Account retrieveAccount(Long accountId);

  Account updateAccount(Long accountId, Account account);

}
