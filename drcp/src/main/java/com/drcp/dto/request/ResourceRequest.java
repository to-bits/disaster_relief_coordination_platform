package com.drcp.dto.request;

import com.drcp.entity.enums.ResourceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRequest {

    @NotNull(message = "Resource type is required")
    private ResourceType type;

    @NotBlank(message = "Resource name is required")
    private String resourceName;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotBlank(message = "Unit is required")
    private String unit;

    private String warehouseLocation;

    private String remarks;

}