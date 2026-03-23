package com.pedroostanik.rifa.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "raffle")
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaffleStatus status;

    protected Raffle() {
        // JPA only
    }

    public Raffle(String title) {
        this.title = title;
        this.status = RaffleStatus.OPEN;
    }

    public void close() {
        this.status = RaffleStatus.CLOSED;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public RaffleStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Raffle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
