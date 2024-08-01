package com.supplyhouse.invitation;

import com.supplyhouse.account.Account;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "invitations")
public class Invitation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "sender_id", nullable = false, updatable = false)
  private Account sender;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "receiver_id", nullable = false, updatable = false)
  private Account receiver;

  @Column(nullable = false)
  private LocalDate sentOn;

  private LocalDate respondedOn;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InvitationStatus status;

  public Long getId() {
    return id;
  }

  public Account getSender() {
    return sender;
  }

  public Account getReceiver() {
    return receiver;
  }

  public LocalDate getSentOn() {
    return sentOn;
  }

  public LocalDate getRespondedOn() {
    return respondedOn;
  }

  public InvitationStatus getStatus() {
    return status;
  }

  public void create(Account sender, Account receiver) {
    this.sender = sender;
    this.receiver = receiver;
    this.sentOn = LocalDate.now();
    this.status = InvitationStatus.PENDING;
  }

  public void accept() {
    this.status = InvitationStatus.ACCEPTED;
    this.respondedOn = LocalDate.now();
    this.receiver.link(sender.getId());
  }

  public void decline() {
    this.status = InvitationStatus.DECLINED;
    this.respondedOn = LocalDate.now();
  }
}
