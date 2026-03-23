package com.pedroostanik.rifa.service;

import com.pedroostanik.rifa.domain.RaffleSlot;
import com.pedroostanik.rifa.repository.RaffleSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final RaffleSlotRepository raffleSlotRepository;

    public PaymentService(RaffleSlotRepository raffleSlotRepository) {
        this.raffleSlotRepository = raffleSlotRepository;
    }

    @Transactional
    public void confirmPayment(Long slotId) {
        RaffleSlot slot = raffleSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found"));

        slot.markAsPaid(); // regra fica na entidade
    }
}
