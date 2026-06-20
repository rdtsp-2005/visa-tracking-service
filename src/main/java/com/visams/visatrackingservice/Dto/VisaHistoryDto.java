package com.visams.visatrackingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisaHistoryDto {

    private Integer historyId;
    private Integer visaId;
    private Long touristId;
    private String action;
    private String remarks;
    private LocalDateTime actionDate;

}
