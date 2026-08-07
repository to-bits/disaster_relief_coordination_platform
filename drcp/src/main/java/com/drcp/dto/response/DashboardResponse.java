package com.drcp.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    /* ---------------- USERS ---------------- */

    private Long totalUsers;

    /* ---------------- DISASTERS ---------------- */

    private Long totalDisasters;

    private Long activeDisasters;

    private Long completedDisasters;

    /* ---------------- RELIEF REQUESTS ---------------- */

    private Long totalReliefRequests;

    private Long pendingRequests;

    private Long approvedRequests;

    private Long completedRequests;

    /* ---------------- VOLUNTEERS ---------------- */

    private Long totalVolunteers;

    private Long availableVolunteers;

    private Long busyVolunteers;

    /* ---------------- DONATIONS ---------------- */

    private Long totalDonations;

    private Integer totalDonationQuantity;

    private Double totalDonationValue;

    /* ---------------- RESOURCES ---------------- */

    private Long totalResources;

    private Integer totalAvailableResources;

    /* ---------------- DISTRIBUTIONS ---------------- */

    private Long totalDistributions;

}