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
@Builder
public class VisaExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer extensionId;

    @Column(nullable = false)
    private Integer visaId;

    @Column(nullable = false)
    private LocalDate extendedDate;

    @Column(nullable = false)
    private String reason;

    private LocalDate requestedOn;

}
