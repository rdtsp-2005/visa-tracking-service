package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "travel_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(name = "tourist_id", nullable = false)
    private Long touristId;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}
