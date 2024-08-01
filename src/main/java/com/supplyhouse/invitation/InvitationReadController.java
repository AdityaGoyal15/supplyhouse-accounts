package com.supplyhouse.invitation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationReadController {

  private final InvitationReadService invitationReadService;

  public InvitationReadController(InvitationReadService invitationReadService) {
    this.invitationReadService = invitationReadService;
  }

  @GetMapping("{id}")
  public ResponseEntity<Invitation> findById(@PathVariable Long id) {
    return ResponseEntity.ok(invitationReadService.findById(id));
  }
}
