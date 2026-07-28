package com.drcp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerRequest {

    @NotBlank
    private String skills;

    @NotBlank
    private String currentLocation;

}