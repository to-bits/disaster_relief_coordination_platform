package com.drcp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReliefDistributionRequest {

    @NotNull(message = "Relief request ID is required")
    private Long reliefRequestId;

    @NotNull(message = "Resource ID is required")
    private Long resourceId;

    @NotNull(message = "Volunteer ID is required")
    private Long volunteerId;

    @NotNull(message = "Disaster ID is required")
    private Long disasterId;

    @NotNull(message = "Distributed quantity is required")
    @Min(value = 1, message = "Distributed quantity must be at least 1")
    private Integer distributedQuantity;

}