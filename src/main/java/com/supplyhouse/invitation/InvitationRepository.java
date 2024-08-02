package com.supplyhouse.invitation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  @Query(
      "SELECT i "
          + "FROM Invitation i "
          + "WHERE i.sender.id = :senderId "
          + "  AND i.receiver.id = :receiverId "
          + "  AND i.status = 'PENDING'")
  Invitation findPendingBySenderIdAndReceiverId(
      @Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

  @Query(
      "SELECT i "
          + "FROM Invitation i "
          + "WHERE i.receiver.id = :receiverId "
          + "  AND i.sender.id = :senderId "
          + "  AND i.status = 'ACCEPTED' "
          + "ORDER BY i.respondedOn DESC")
  List<Invitation> findAllAcceptedBySenderIdAndReceiverIdOrderByRespondedOnDesc(
      @Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
