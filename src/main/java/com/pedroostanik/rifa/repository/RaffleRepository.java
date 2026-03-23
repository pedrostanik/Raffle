package com.pedroostanik.rifa.repository;

import com.pedroostanik.rifa.domain.Raffle;
import com.pedroostanik.rifa.domain.RaffleSlot;
import com.pedroostanik.rifa.domain.RaffleSlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaffleRepository extends JpaRepository<Raffle, Long> {

    List<RaffleSlot> findByStatus(RaffleSlotStatus status);
}
