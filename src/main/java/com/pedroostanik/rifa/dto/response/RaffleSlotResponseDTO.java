package com.pedroostanik.rifa.dto.response;

import com.pedroostanik.rifa.domain.RaffleSlot;

public record RaffleSlotResponseDTO(
        Long id,
        String value,
        String status,
        String participantName
) {
    public static RaffleSlotResponseDTO fromEntity(RaffleSlot slot) {
        return new RaffleSlotResponseDTO(
                slot.getId(),
                slot.getValue(),
                slot.getStatus().name(),
                slot.getParticipant() != null ? slot.getParticipant().getName() : null
        );
    }
}