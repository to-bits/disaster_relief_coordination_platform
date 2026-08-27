package com.drcp.dto.request;

import com.drcp.entity.enums.DisasterType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisasterRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Disaster type is required")
    private DisasterType type;

    @NotBlank(message = "District is required")
    private String district;

    private String upazila;

    private Double latitude;

    private Double longitude;

    @Min(value = 0, message = "Affected people count cannot be negative")
    private Integer affectedPeople;

}