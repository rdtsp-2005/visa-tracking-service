package com.visams.visatrackingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaDto {

    private Integer visaId;
    private Integer touristId;
    private String visaType;
    private LocalDate issueDate;
    private LocalTime expiryData;
    private String status;

}
