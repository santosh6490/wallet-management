package com.isobar.wallet.mapper;

import com.isobar.wallet.dto.StatementDTO;
import com.isobar.wallet.entity.Statement;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
  public StatementDTO map(Statement source, StatementDTO destination){
    destination.setId(source.getId());
    destination.setAccountId(source.getAccountId());
    destination.setAmount(source.getAmount());
    destination.setType(source.getType());
    destination.setTransactionTime(source.getTransactionTime());
    return destination;
  }
}
