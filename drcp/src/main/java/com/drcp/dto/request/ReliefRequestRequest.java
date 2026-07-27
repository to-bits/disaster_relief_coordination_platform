package com.drcp.dto.request;

import com.drcp.entity.enums.PriorityLevel;
import com.drcp.entity.enums.ReliefType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReliefRequestRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private ReliefType reliefType;

    @Builder.Default
    private PriorityLevel priority = PriorityLevel.MEDIUM;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String contactPhone;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private Long disasterId;

}