package com.supplyhouse.invitation;

import com.supplyhouse.invitation.dto.SendInvitationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationWriteController {

  private static final Logger LOGGER = LoggerFactory.getLogger(InvitationWriteController.class);
  private final InvitationWriteService invitationWriteService;

  public InvitationWriteController(InvitationWriteService invitationWriteService) {
    this.invitationWriteService = invitationWriteService;
  }

  @PostMapping
  public ResponseEntity<Invitation> send(@RequestBody SendInvitationDto sendInvitationDto) {
    try {
      return ResponseEntity.ok(invitationWriteService.send(sendInvitationDto));
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("{id}/accept")
  public ResponseEntity<Invitation> accept(@PathVariable("id") Long id) {
    try {
      return ResponseEntity.ok(invitationWriteService.accept(id));
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("{id}/decline")
  public ResponseEntity<Invitation> decline(@PathVariable("id") Long id) {
    try {
      return ResponseEntity.ok(invitationWriteService.decline(id));
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
