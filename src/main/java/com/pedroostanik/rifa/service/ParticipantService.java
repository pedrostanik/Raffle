package com.pedroostanik.rifa.service;

import com.pedroostanik.rifa.domain.Participant;
import com.pedroostanik.rifa.repository.ParticipantRepository;
import org.springframework.stereotype.Service;


@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant getOrCreate(String name) {

        return participantRepository.findByName(name)
                .orElseGet(() -> participantRepository.save(new Participant(name)));


    }
}
