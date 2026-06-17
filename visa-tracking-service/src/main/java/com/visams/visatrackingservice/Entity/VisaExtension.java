package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "visa_extensions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class VisaExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer extensionId;

    @ManyToOne
    @JoinColumn(name = "visa_id", nullable = false)
    private Visa visa;

    @Column(nullable = false)
    private LocalDate extendedDate;

    @Column(nullable = false)
    private String reason;

}
