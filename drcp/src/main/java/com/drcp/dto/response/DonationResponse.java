package com.drcp.dto.response;

import com.drcp.entity.enums.DonationStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationResponse {

    private Long id;

    private Long donorId;

    private String donorName;

    private Long disasterId;

    private String disasterTitle;

    private String itemName;

    private Integer quantity;

    private String remarks;

    private DonationStatus status;

}