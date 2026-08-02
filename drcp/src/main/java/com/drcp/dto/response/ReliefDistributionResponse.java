package com.drcp.dto.response;

import com.drcp.entity.enums.DistributionStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReliefDistributionResponse {

    private Long id;

    private Long reliefRequestId;

    private String reliefTitle;

    private Long resourceId;

    private String resourceName;

    private Long volunteerId;

    private String volunteerName;

    private Long disasterId;

    private String disasterTitle;

    private Integer distributedQuantity;

    private DistributionStatus status;

}