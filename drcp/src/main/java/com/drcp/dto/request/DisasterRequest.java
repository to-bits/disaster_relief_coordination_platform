package com.drcp.dto.request;

import com.drcp.entity.DisasterSeverity;
import com.drcp.entity.DisasterStatus;
import com.drcp.entity.DisasterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DisasterRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Disaster type is required")
    private DisasterType disasterType;

    @NotNull(message = "Severity is required")
    private DisasterSeverity severity;

    @NotBlank(message = "Location is required")
    private String location;

    private Double latitude;

    private Double longitude;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Status is required")
    private DisasterStatus status;

}