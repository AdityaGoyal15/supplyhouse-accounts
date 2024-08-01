package com.supplyhouse.invitation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationWriteController {

  private final InvitationWriteService invitationWriteService;

  public InvitationWriteController(InvitationWriteService invitationWriteService) {
    this.invitationWriteService = invitationWriteService;
  }

  @PostMapping("{senderId}/{receiverId}")
  public ResponseEntity<Invitation> send(
      @PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
    return ResponseEntity.ok(invitationWriteService.send(senderId, receiverId));
  }

  @PutMapping("{id}/accept")
  public ResponseEntity<Invitation> accept(@PathVariable("id") Long id) {
    return ResponseEntity.ok(invitationWriteService.accept(id));
  }

  @PutMapping("{id}/decline")
  public ResponseEntity<Invitation> decline(@PathVariable("id") Long id) {
    return ResponseEntity.ok(invitationWriteService.decline(id));
  }
}
