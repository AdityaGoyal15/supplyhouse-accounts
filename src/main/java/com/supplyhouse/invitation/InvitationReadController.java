package com.supplyhouse.invitation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationReadController {

  private static final Logger LOGGER = LoggerFactory.getLogger(InvitationReadController.class);
  private final InvitationReadService invitationReadService;

  public InvitationReadController(InvitationReadService invitationReadService) {
    this.invitationReadService = invitationReadService;
  }

  @GetMapping("{id}")
  public ResponseEntity<Invitation> findById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(invitationReadService.findById(id));
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
}
