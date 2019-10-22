package com.isobar.wallet.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STATEMENT")
public class Statement {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "account_id", nullable = false, insertable = false, updatable = false)
  private Account account;

  @Column(name = "account_id")
  private Long accountId;

  private String type;

  @Column(precision = 12, scale = 3)
  private BigDecimal amount;

  @Basic private LocalDateTime transactionTime;

  public Statement() {
  }

  public Statement(Long id, Account account, Long accountId, String type,
      BigDecimal amount, LocalDateTime transactionTime) {
    this.id = id;
    this.account = account;
    this.accountId = accountId;
    this.type = type;
    this.amount = amount;
    this.transactionTime = transactionTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Statement statement = (Statement) o;
    return Objects.equals(id, statement.id) &&
        Objects.equals(accountId, statement.accountId) &&
        Objects.equals(type, statement.type) &&
        Objects.equals(amount, statement.amount) &&
        Objects.equals(transactionTime, statement.transactionTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, account);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
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
