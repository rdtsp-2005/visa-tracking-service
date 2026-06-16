package com.visams.visatrackingservice.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelLogDto {

    private Integer logId;
    private Integer touristId;
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
