package com.visams.visatrackingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "location_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(nullable = false)
    private Integer touristId;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private String locationType;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}
