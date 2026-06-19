package com.visams.visatrackingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationHistoryDto {

    private Integer locationId;
    private Long touristId;
    private String locationName;
    private String locationType;
    private LocalDateTime recordedAt;

}
