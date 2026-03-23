package com.pedroostanik.rifa.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
        name = "raffle_slot",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"raffle_id", "slot_value"})
        }
)
public class RaffleSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "raffle_id")
    private Raffle raffle;

    @Column(name = "slot_value", nullable = false)
    private String value; // nome ou número da rifa

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaffleSlotStatus status;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private Instant reservedAt;

    protected RaffleSlot() {
    }

    public RaffleSlot(Raffle raffle, String value) {
        this.raffle = raffle;
        this.value = value;
        this.status = RaffleSlotStatus.AVAILABLE;
    }

    public void markAsPaid() {
        this.status = RaffleSlotStatus.PAID;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public RaffleSlotStatus getStatus() {
        return status;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void reserve(Participant participant) {

        if (raffle.getStatus() == RaffleStatus.CLOSED) {
            throw new IllegalStateException("Raffle is closed");
        }

        if (this.status != RaffleSlotStatus.AVAILABLE) {
            throw new IllegalStateException("Slot not available");
        }

        this.participant = participant;
        this.status = RaffleSlotStatus.RESERVED;
        this.reservedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "RaffleSlot{" +
                "id=" + id +
                ", raffle=" + raffle +
                ", value='" + value + '\'' +
                ", status=" + status +
                ", participant=" + participant +
                ", reservedAt=" + reservedAt +
                '}';
    }
}
