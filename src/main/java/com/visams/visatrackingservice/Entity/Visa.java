package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VisaHistory> visaHistories;

    @Column(name = "tourist_id", nullable = false)
    private Long touristId;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL)
    private List<VisaExtension> visaExtensions;

    @Column(nullable = false)
    private String visaType;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String status;
}
