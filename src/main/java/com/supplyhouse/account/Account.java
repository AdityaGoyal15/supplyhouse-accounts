package com.supplyhouse.account;

import com.supplyhouse.account.dto.CreateAccountDTO;
import com.supplyhouse.account.dto.UpdateAccountDTO;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String holderName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountType accountType = AccountType.REGULAR;

  private Long businessAccountId;

  @Column(nullable = false, updatable = false)
  private LocalDate createdOn;

  @OneToMany(mappedBy = "account")
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "sender")
  private List<Invitation> invitationsSent = new ArrayList<>();

  @OneToMany(mappedBy = "receiver")
  private List<Invitation> invitationsReceived = new ArrayList<>();

  private boolean shareAllHistory;

  public void create(CreateAccountDTO createAccountDTO) {
    this.holderName = createAccountDTO.holderName();
    this.email = createAccountDTO.email();
    this.createdOn = LocalDate.now();
  }

  public void update(UpdateAccountDTO updateAccountDTO) {
    this.holderName = updateAccountDTO.holderName();
    this.email = updateAccountDTO.email();
    this.shareAllHistory = updateAccountDTO.shareAllHistory();
  }

  public Long getId() {
    return id;
  }

  public String getHolderName() {
    return holderName;
  }

  public String getEmail() {
    return email;
  }

  public Long getBusinessAccountId() {
    return businessAccountId;
  }

  public LocalDate getCreatedOn() {
    return createdOn;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public boolean isShareAllHistory() {
    return shareAllHistory;
  }

  public List<Invitation> getInvitationsSent() {
    return invitationsSent;
  }

  public List<Invitation> getInvitationsReceived() {
    return invitationsReceived;
  }

  public void link(Long businessAccountId) {
    this.businessAccountId = businessAccountId;
  }

  public void upgrade() {
    this.accountType = AccountType.BUSINESS;
  }

  public void unlink() {
    this.businessAccountId = null;
  }
}
