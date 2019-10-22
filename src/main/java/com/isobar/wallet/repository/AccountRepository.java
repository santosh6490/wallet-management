package com.isobar.wallet.repository;

import com.isobar.wallet.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {}
