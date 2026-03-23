package com.pedroostanik.rifa.repository;

import com.pedroostanik.rifa.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    boolean existsByName(String name);
    Optional<Participant> findByName(String name);
}
