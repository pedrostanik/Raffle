package com.pedroostanik.rifa.service;

import com.pedroostanik.rifa.domain.*;
import com.pedroostanik.rifa.repository.RaffleRepository;
import com.pedroostanik.rifa.repository.RaffleSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class RaffleService {

    private final RaffleSlotRepository raffleSlotRepository;
    private final RaffleRepository raffleRepository;

    public RaffleService(RaffleSlotRepository raffleSlotRepository, RaffleRepository raffleRepository ) {
        this.raffleSlotRepository = raffleSlotRepository;
        this.raffleRepository = raffleRepository;
    }

    @Transactional
    public void reserveSlot(Long slotId, Participant participant) {

        RaffleSlot slot = raffleSlotRepository
                .lockSlot(slotId)
                .orElseThrow(() -> new IllegalStateException("Slot not available"));

        slot.reserve(participant);
    }


    public Raffle createRaffle(String title, List<String> slots) {

        Raffle raffle = new Raffle(title);
        raffleRepository.save(raffle);

        slots.forEach(slotValue -> {
            RaffleSlot slot = new RaffleSlot(raffle, slotValue);
            raffleSlotRepository.save(slot);
        });

        return raffle;
    }

    public List<Raffle> listaRaffles() {
        return raffleRepository.findAll();

    }

    public List<RaffleSlot> getAvailablesRafflesSlot(Long idRaffle) {
        return raffleSlotRepository.findByRaffleIdAndStatus(idRaffle, RaffleSlotStatus.AVAILABLE);
    }

    public Optional<RaffleSlot> raffleSlotChoosed(Long slotChoosed) {
        return raffleSlotRepository.findById(slotChoosed);
    }

    public void saveRaffle(Raffle raffle) {
        raffleRepository.save(raffle);
    }

    public void saveRaffleSlot(RaffleSlot raffleSlot) {
        raffleSlotRepository.save(raffleSlot);
    }

    @Transactional
    public void closeRaffle(Long raffleId) {

        Raffle raffle = raffleRepository.findById(raffleId)
                .orElseThrow(() -> new IllegalArgumentException("Raffle not found"));

        if (raffle.getStatus() == RaffleStatus.CLOSED) {
            throw new IllegalStateException("Raffle already closed");
        }

        raffle.close();
    }

    @Transactional
    public void registerParticipant(RaffleService raffleService, ParticipantService participantService,
                                    Long raffleChoosed, Long slotChoosed, String nome) {

        Participant participantSaved = participantService.getOrCreate(nome);

        Optional<RaffleSlot> raffleSlotChoosed = raffleService.raffleSlotChoosed(slotChoosed);
        System.out.println("Choosed: " + raffleSlotChoosed);

        raffleService.reserveSlot(slotChoosed, participantSaved);

    }

}