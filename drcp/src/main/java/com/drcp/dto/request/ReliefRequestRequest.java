package com.drcp.dto.request;

import com.drcp.entity.enums.PriorityLevel;
import com.drcp.entity.enums.ReliefType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReliefRequestRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Relief type is required")
    private ReliefType reliefType;

    @Builder.Default
    private PriorityLevel priority = PriorityLevel.MEDIUM;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Contact phone is required")
    private String contactPhone;

    @NotBlank(message = "Delivery address is required")
    private String deliveryAddress;

    @NotNull(message = "Disaster ID is required")
    private Long disasterId;

}