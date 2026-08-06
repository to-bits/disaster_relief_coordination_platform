package com.drcp.dto.request;

import com.drcp.entity.enums.DonationStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationRequest {

    @NotNull
    private Long donorId;

    @NotNull
    private Long disasterId;

    @NotNull
    private Long resourceId;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @Min(0)
    private Double estimatedValue;

    private String remarks;

    @Builder.Default
    private DonationStatus status = DonationStatus.PENDING;
}