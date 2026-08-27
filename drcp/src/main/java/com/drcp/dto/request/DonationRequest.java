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

    @NotNull(message = "Donor ID is required")
    private Long donorId;

    @NotNull(message = "Disaster ID is required")
    private Long disasterId;

    @NotNull(message = "Resource ID is required")
    private Long resourceId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Estimated value is required")
    @Min(value = 0, message = "Estimated value cannot be negative")
    private Double estimatedValue;

    private String remarks;

    @Builder.Default
    private DonationStatus status = DonationStatus.PENDING;
}