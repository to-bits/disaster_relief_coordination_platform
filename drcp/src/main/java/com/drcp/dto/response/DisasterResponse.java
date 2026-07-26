package com.drcp.dto.response;

import com.drcp.entity.DisasterStatus;
import com.drcp.entity.DisasterType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisasterResponse {

    private Long id;

    private String title;

    private String description;

    private DisasterType type;

    private DisasterStatus status;

    private String district;

    private String upazila;

    private Double latitude;

    private Double longitude;

    private Integer affectedPeople;

    private String reportedBy;

    private LocalDateTime createdAt;

}