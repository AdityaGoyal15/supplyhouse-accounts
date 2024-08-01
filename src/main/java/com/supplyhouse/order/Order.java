package com.supplyhouse.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supplyhouse.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false, updatable = false)
  @JsonIgnore
  private Account account;

  @Column(nullable = false, updatable = false)
  private LocalDate placedOn = LocalDate.now();

  public void create(Account account) {
    this.account = account;
    this.placedOn = LocalDate.now();
  }

  public Long getId() {
    return id;
  }

  public Account getAccount() {
    return account;
  }

  public LocalDate getPlacedOn() {
    return placedOn;
  }
}
