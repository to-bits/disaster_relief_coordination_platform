package com.drcp.dto.request;

import com.drcp.entity.enums.DisasterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisasterRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private DisasterType type;

    @NotBlank
    private String district;

    private String upazila;

    private Double latitude;

    private Double longitude;

    private Integer affectedPeople;

}