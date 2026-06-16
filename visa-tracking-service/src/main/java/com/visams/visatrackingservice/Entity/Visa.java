package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "visas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Visa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visaId;

    @Column(nullable = false)
    private Integer touristId;

    @Column(nullable = false)
    private String visaType;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String status;
}
