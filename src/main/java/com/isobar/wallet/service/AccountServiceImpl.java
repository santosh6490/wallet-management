package com.isobar.wallet.service;

import com.isobar.wallet.entity.Account;
import com.isobar.wallet.exception.AccountNotFoundException;
import com.isobar.wallet.repository.AccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Long createAccount(final Account account) {
    final Account createdAccount = accountRepository.save(account);
    return createdAccount.getId();
  }

  @Override
  public Account retrieveAccount(final Long accountId) {
    final Optional<Account> accountOpt = accountRepository.findById(accountId);
    if(accountOpt.isPresent()){
      return accountOpt.get();
    }else{
      throw new AccountNotFoundException("Invalid account Id");
    }

  }

  @Override
  public Account updateAccount(final Long accountId,final Account account) {
    final Optional<Account> accountOpt = accountRepository.findById(accountId);
    accountOpt.ifPresent((acc) -> {
      if(accountId.equals(acc.getId())){
        accountRepository.save(account);
      }else{
        throw new AccountNotFoundException("No corresponding account found");
      }
    });
    return account;
  }
}
