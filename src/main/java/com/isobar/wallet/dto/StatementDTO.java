package com.isobar.wallet.dto;

import com.isobar.wallet.entity.Statement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StatementDTO {

  private Long id;
  private Long accountId;
  private String type;
  private BigDecimal amount;
  private LocalDateTime transactionTime;

  public static final StatementDTO from(Statement s) {
    return new StatementDTO(s.getId(), s.getAccountId(), s.getType(), s.getAmount(), s.getTransactionTime());
  }

  public StatementDTO() {}

  public StatementDTO(
      Long id, Long accountId, String type, BigDecimal amount, LocalDateTime transactionTime) {
    this.id = id;
    this.accountId = accountId;
    this.type = type;
    this.amount = amount;
    this.transactionTime = transactionTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(LocalDateTime transactionTime) {
    this.transactionTime = transactionTime;
  }
}
