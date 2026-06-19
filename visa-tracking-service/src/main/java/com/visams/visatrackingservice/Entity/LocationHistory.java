package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "location_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(name = "tourist_id", nullable = false)
    private Long touristId;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private String locationType;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}