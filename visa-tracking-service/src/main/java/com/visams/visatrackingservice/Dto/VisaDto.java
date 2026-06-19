package com.visams.visatrackingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaDto {

    private Integer visaId;
    private Long touristId;
    private String visaType;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String status;

}
