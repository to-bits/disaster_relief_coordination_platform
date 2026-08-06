package com.drcp.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    /* ---------------- Users ---------------- */

    private Long totalUsers;

    /* ---------------- Disaster ---------------- */

    private Long totalDisasters;

    private Long activeDisasters;

    private Long completedDisasters;

    /* ---------------- Relief Requests ---------------- */

    private Long totalReliefRequests;

    private Long pendingReliefRequests;

    private Long approvedReliefRequests;

    private Long completedReliefRequests;

    /* ---------------- Volunteers ---------------- */

    private Long totalVolunteers;

    private Long availableVolunteers;

    private Long busyVolunteers;

    /* ---------------- Donations ---------------- */

    private Long totalDonations;

    private Double totalDonationAmount;

    /* ---------------- Resources ---------------- */

    private Long totalResources;

    private Integer totalAvailableResourceQuantity;

    /* ---------------- Distribution ---------------- */

    private Long totalDistributions;

}