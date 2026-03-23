package com.pedroostanik.rifa.repository;

import com.pedroostanik.rifa.domain.Participant;
import com.pedroostanik.rifa.domain.RaffleSlot;
import com.pedroostanik.rifa.domain.RaffleSlotStatus;
import com.pedroostanik.rifa.domain.RaffleStatus;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RaffleSlotRepository extends JpaRepository<RaffleSlot, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
      SELECT s FROM RaffleSlot s
      WHERE s.id = :slotId AND s.status = 'AVAILABLE'
    """)
    Optional<RaffleSlot> lockSlot(@Param("slotId") Long slotId);

    public List<RaffleSlot> findByRaffleId(Long idRaffle);
    public List<RaffleSlot> findByRaffleIdAndStatus(Long idRaffle, RaffleSlotStatus status);

    public Optional<RaffleSlot> findById(Long id);




//    @Transactional
//    @Modifying
//    @Query("""
//    UPDATE RaffleSlot rs
//    SET rs.status = :status,
//        rs.participant = :participant
//    WHERE rs.id = :slotId
//""")
//    void updateReservation(
//            @Param("slotId") Long slotId,
//            @Param("status") RaffleSlotStatus status,
//            @Param("participant") Participant participant
//    );


}
