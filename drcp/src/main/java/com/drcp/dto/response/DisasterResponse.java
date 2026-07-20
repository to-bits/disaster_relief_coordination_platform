package com.drcp.dto.response;

import com.drcp.entity.DisasterSeverity;
import com.drcp.entity.DisasterStatus;
import com.drcp.entity.DisasterType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DisasterResponse {

    private Long id;

    private String title;

    private String description;

    private DisasterType disasterType;

    private DisasterSeverity severity;

    private String location;

    private Double latitude;

    private Double longitude;

    private LocalDate startDate;

    private LocalDate endDate;

    private DisasterStatus status;

}