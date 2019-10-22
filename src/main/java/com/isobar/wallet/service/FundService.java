package com.isobar.wallet.service;

import java.math.BigDecimal;

public interface FundService {

  Boolean debit(Long accountId, BigDecimal amount);

  Boolean credit(Long accountId, BigDecimal amount);
}
