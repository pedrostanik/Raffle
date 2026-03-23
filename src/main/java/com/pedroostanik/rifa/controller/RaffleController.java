package com.pedroostanik.rifa.controller;

import com.pedroostanik.rifa.domain.RaffleSlot;
import com.pedroostanik.rifa.dto.request.CreateRaffleRequest;
import com.pedroostanik.rifa.dto.response.RaffleResponseDTO;
import com.pedroostanik.rifa.dto.response.RaffleSlotResponseDTO;
import com.pedroostanik.rifa.service.ParticipantService;
import com.pedroostanik.rifa.service.RaffleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raffles")
public class RaffleController {

    private final RaffleService raffleService;

    public RaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @GetMapping
    public List<RaffleResponseDTO> listAll() {
        return raffleService.listaRaffles()
                .stream()
                .map(RaffleResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/slots")
    public List<RaffleSlotResponseDTO> listAllSlots(@RequestParam Long raffleId) {
        return raffleService.getAvailablesRafflesSlot(raffleId)
                .stream()
                .map(RaffleSlotResponseDTO::fromEntity)
                .toList();
    }

    @PostMapping
    public RaffleResponseDTO create(@RequestBody CreateRaffleRequest request) {
        return RaffleResponseDTO.fromEntity(
                raffleService.createRaffle(request.title(), request.slots())
        );
    }

    @PostMapping("/{raffleId}/close")
    public void close(@PathVariable Long raffleId) {
        raffleService.closeRaffle(raffleId);
    }

    @PostMapping("/reserve_slot")
    public void reserveSlot(@PathVariable Long raffleId, @PathVariable Long reserveSlotId) {
        raffleService.closeRaffle(raffleId);
    }


//    registerParticipant(RaffleService raffleService, ParticipantService participantService, Long slotChoosed, String nome)
}