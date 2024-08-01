package com.supplyhouse.invitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  @Query("SELECT i FROM Invitation i WHERE i.sender.id = :senderId AND i.receiver.id = :receiverId")
  Invitation findBySenderIdAndReceiverId(
      @Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
