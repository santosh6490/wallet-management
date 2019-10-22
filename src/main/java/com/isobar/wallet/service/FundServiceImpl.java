package com.isobar.wallet.service;

import com.isobar.wallet.entity.Account;
import com.isobar.wallet.entity.Statement;
import com.isobar.wallet.exception.AccountNotFoundException;
import com.isobar.wallet.exception.InsufficientBalanceException;
import com.isobar.wallet.repository.AccountRepository;
import com.isobar.wallet.repository.StatementRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundServiceImpl implements FundService {

  @Autowired private AccountRepository accountRepository;

  @Autowired private StatementRepository statementRepository;

  @Override
  public Boolean debit(Long accountId, BigDecimal amount) {
    final Optional<Account> optionalAccount = accountRepository.findById(accountId);
    if (optionalAccount.isPresent()) {
      final Account account = optionalAccount.get();
      final BigDecimal accountBalance = account.getAccountBalance();
      if(accountBalance.compareTo(amount) < 0){
        throw new InsufficientBalanceException("Insufficient Balance.");
      }
      final BigDecimal updatedAmount = accountBalance.subtract(amount);
      account.setAccountBalance(updatedAmount);
      accountRepository.save(account);
      statementRepository.save(createStatement(account, amount, "debit"));
    } else {
      throw new AccountNotFoundException("Invalid account id " + accountId);
    }
    return true;
  }

  @Override
  public Boolean credit(Long accountId, BigDecimal amount) {
    final Optional<Account> optionalAccount = accountRepository.findById(accountId);
    if (optionalAccount.isPresent()) {
      final Account account = optionalAccount.get();
      final BigDecimal updatedBalance = account.getAccountBalance().add(amount);
      account.setAccountBalance(updatedBalance);
      accountRepository.save(account);
      statementRepository.save(createStatement(account, amount, "credit"));
    } else {
      throw new AccountNotFoundException("Invalid account id " + accountId);
    }
    return true;
  }

  private Statement createStatement(Account account, BigDecimal amount, String type) {
    return new Statement(null, account,account.getId(), type, amount, LocalDateTime.now());
  }
}
