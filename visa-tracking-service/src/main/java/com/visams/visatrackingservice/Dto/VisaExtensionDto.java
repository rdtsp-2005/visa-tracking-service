package com.visams.visatrackingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaExtensionDto {

    private Integer extensionId;
    private Integer visaId;
    private LocalDate extendedDate;
    private String reason;

}
