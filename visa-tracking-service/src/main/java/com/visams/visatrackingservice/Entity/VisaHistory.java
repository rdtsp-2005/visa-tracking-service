package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "visa_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisaHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @Column(nullable = false)
    private Integer visaId;

    @Column(nullable = false)
    private Integer touristId;

    @Column(nullable = false)
    private String action;

    private String remarks;

    @Column(nullable = false)
    private LocalDateTime actionDate;
}
