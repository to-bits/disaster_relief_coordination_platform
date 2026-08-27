package com.drcp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerRequest {

    @NotBlank(message = "Skills are required")
    private String skills;

    @NotBlank(message = "Current location is required")
    private String currentLocation;

}