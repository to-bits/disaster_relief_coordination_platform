package com.drcp.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReliefDistributionRequest {

    @NotNull
    private Long reliefRequestId;

    @NotNull
    private Long resourceId;

    @NotNull
    private Long volunteerId;

    @NotNull
    private Long disasterId;

    @NotNull
    private Integer distributedQuantity;

}