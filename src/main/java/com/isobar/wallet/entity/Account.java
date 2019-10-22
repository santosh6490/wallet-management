package com.isobar.wallet.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ACCOUNT")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(max = 100)
  private String name;

  @Size(max = 15)
  private String mobile;

  @NotNull
  @Email
  @Size(max = 100)
  @Column(unique = true)
  private String email;

  @NotNull
  @Size(max = 128)
  private String password;

  private String address;

  @Column(precision = 12,scale = 3)
  private BigDecimal accountBalance;

  public Account() {}

  public Account(Long id, String name, String mobile, String email, String password,
      String address, BigDecimal accountBalance) {
    this.id = id;
    this.name = name;
    this.mobile = mobile;
    this.email = email;
    this.password = password;
    this.address = address;
    this.accountBalance = accountBalance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(id, account.id)
        && Objects.equals(name, account.name)
        && Objects.equals(mobile, account.mobile)
        && Objects.equals(email, account.email)
        && Objects.equals(password, account.password)
        && Objects.equals(address, account.address)
        && Objects.equals(accountBalance, account.accountBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public BigDecimal getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(BigDecimal accountBalance) {
    this.accountBalance = accountBalance;
  }
}
