package com.visams.visatrackingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelLogDto {

    private Integer logId;
    private Long touristId;
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
