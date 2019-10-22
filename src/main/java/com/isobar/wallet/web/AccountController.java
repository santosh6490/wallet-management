package com.isobar.wallet.web;

import com.isobar.wallet.entity.Account;
import com.isobar.wallet.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  private static final Logger LOGGER= LoggerFactory.getLogger(AccountController.class);

  @Autowired private AccountService accountService;

  @PostMapping("/accounts/")
  public Long createAccount(@RequestBody Account account) {
    LOGGER.info("Creating account");
    final Long accountId = accountService.createAccount(account);
    LOGGER.info("Created account with id : {}", accountId);
    return accountId;
  }

  @GetMapping("/accounts/{id}")
  public Account retrieveAccount(@PathVariable Long id){
    LOGGER.info("Retrieving account for accountId : {}", id);
    return accountService.retrieveAccount(id);
  }

  @PutMapping("/accounts/{id}")
  public Account updateAccount(@PathVariable Long id, @RequestBody Account account){
    LOGGER.info("Update account for accountId : {}", id);
    final Account updateAccount = accountService.updateAccount(id, account);
    LOGGER.info("Updated account for accountId : {}", account.getId());
    return updateAccount;
  }

}
