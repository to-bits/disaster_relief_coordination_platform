package com.drcp.service.impl;

import com.drcp.dto.response.DashboardResponse;
import com.drcp.entity.enums.DisasterStatus;
import com.drcp.entity.enums.ReliefRequestStatus;
import com.drcp.entity.enums.VolunteerStatus;
import com.drcp.repository.*;
import com.drcp.service.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final DisasterRepository disasterRepository;

    private final ReliefRequestRepository reliefRequestRepository;

    private final VolunteerRepository volunteerRepository;

    private final DonationRepository donationRepository;

    private final ResourceRepository resourceRepository;

    private final ReliefDistributionRepository reliefDistributionRepository;

    @Override
    public DashboardResponse getDashboard() {

        // ===========================
        // USERS
        // ===========================

        Long totalUsers = userRepository.count();

        // ===========================
        // DISASTERS
        // ===========================

        Long totalDisasters = disasterRepository.count();

        Long activeDisasters =
                disasterRepository.countByStatus(
                        DisasterStatus.ACTIVE
                );

        Long completedDisasters =
                disasterRepository.countByStatus(
                        DisasterStatus.RESOLVED
                );

        // ===========================
        // RELIEF REQUESTS
        // ===========================

        Long totalReliefRequests =
                reliefRequestRepository.count();

        Long pendingRequests =
                reliefRequestRepository.countByStatus(
                        ReliefRequestStatus.PENDING
                );

        Long approvedRequests =
                reliefRequestRepository.countByStatus(
                        ReliefRequestStatus.APPROVED
                );

        Long completedRequests =
                reliefRequestRepository.countByStatus(
                        ReliefRequestStatus.COMPLETED
                );

        // ===========================
        // VOLUNTEERS
        // ===========================

        Long totalVolunteers =
                volunteerRepository.count();

        Long availableVolunteers =
                volunteerRepository.countByAvailableTrue();

        Long busyVolunteers =
                volunteerRepository.countByStatus(
                        VolunteerStatus.BUSY
                );

        // ===========================
        // DONATIONS
        // ===========================

        Long totalDonations =
                donationRepository.count();

        Integer totalDonationQuantity =
                donationRepository.getTotalDonationQuantity();

        Double totalDonationValue =
                donationRepository.getTotalDonationValue();

        // ===========================
        // RESOURCES
        // ===========================

        Long totalResources =
                resourceRepository.count();

        Integer totalAvailableResources =
                resourceRepository.getTotalAvailableQuantity();

        // ===========================
        // RELIEF DISTRIBUTIONS
        // ===========================

        Long totalDistributions =
                reliefDistributionRepository.count();

        // ===========================
        // BUILD RESPONSE
        // ===========================

        return DashboardResponse.builder()

                // USERS
                .totalUsers(totalUsers)

                // DISASTERS
                .totalDisasters(totalDisasters)
                .activeDisasters(activeDisasters)
                .completedDisasters(completedDisasters)

                // RELIEF REQUESTS
                .totalReliefRequests(totalReliefRequests)
                .pendingRequests(pendingRequests)
                .approvedRequests(approvedRequests)
                .completedRequests(completedRequests)

                // VOLUNTEERS
                .totalVolunteers(totalVolunteers)
                .availableVolunteers(availableVolunteers)
                .busyVolunteers(busyVolunteers)

                // DONATIONS
                .totalDonations(totalDonations)
                .totalDonationQuantity(totalDonationQuantity)
                .totalDonationValue(totalDonationValue)

                // RESOURCES
                .totalResources(totalResources)
                .totalAvailableResources(totalAvailableResources)

                // DISTRIBUTIONS
                .totalDistributions(totalDistributions)

                .build();
    }

}