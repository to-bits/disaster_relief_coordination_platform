package com.drcp.dto.response;

import com.drcp.entity.enums.VolunteerStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerResponse {

    private Long id;

    private Long userId;

    private String fullName;

    private String email;

    private String skills;

    private String currentLocation;

    private VolunteerStatus status;

    private Boolean available;

}