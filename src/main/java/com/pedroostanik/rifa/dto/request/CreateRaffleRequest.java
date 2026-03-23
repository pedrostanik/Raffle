package com.pedroostanik.rifa.dto.request;

import java.util.List;

public record CreateRaffleRequest(
        String title,
        List<String> slots
) {}