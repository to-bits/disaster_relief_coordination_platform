package com.drcp.dto.response;

import com.drcp.entity.enums.ResourceType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceResponse {

    private Long id;

    private ResourceType type;

    private String resourceName;

    private Integer quantity;

    private String unit;

    private String warehouseLocation;

    private String remarks;

}