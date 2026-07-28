package com.drcp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationRequest {

    @NotBlank
    private String itemName;

    @NotNull
    private Integer quantity;

    private String remarks;

    @NotNull
    private Long disasterId;

}