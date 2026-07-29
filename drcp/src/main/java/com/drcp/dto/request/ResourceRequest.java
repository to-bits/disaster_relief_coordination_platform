package com.drcp.dto.request;

import com.drcp.entity.enums.ResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRequest {

    @NotNull
    private ResourceType type;

    @NotBlank
    private String resourceName;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String unit;

    private String warehouseLocation;

    private String remarks;

}