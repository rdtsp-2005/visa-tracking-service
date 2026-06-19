package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "visa_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisaHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "visa_id", nullable = false)
    private Visa visa;

    @ManyToOne
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;

    @Column(nullable = false)
    private String action;

    private String remarks;

    @Column(nullable = false)
    private LocalDateTime actionDate;

}
