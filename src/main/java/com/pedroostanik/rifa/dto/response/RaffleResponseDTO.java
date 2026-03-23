package com.pedroostanik.rifa.dto.response;

import com.pedroostanik.rifa.domain.Raffle;

public record RaffleResponseDTO(
        Long id,
        String title,
        String status
) {
    public static RaffleResponseDTO fromEntity(Raffle raffle) {
        return new RaffleResponseDTO(
                raffle.getId(),
                raffle.getTitle(),
                raffle.getStatus().name()
        );
    }
}