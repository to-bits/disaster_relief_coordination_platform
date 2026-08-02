package com.drcp.service.impl;

import com.drcp.dto.request.ReliefDistributionRequest;
import com.drcp.dto.response.ReliefDistributionResponse;
import com.drcp.entity.Disaster;
import com.drcp.entity.ReliefDistribution;
import com.drcp.entity.ReliefRequest;
import com.drcp.entity.Resource;
import com.drcp.entity.User;
import com.drcp.entity.Volunteer;
import com.drcp.entity.enums.DistributionStatus;
import com.drcp.entity.enums.ReliefRequestStatus;
import com.drcp.entity.enums.VolunteerStatus;
import com.drcp.exception.ResourceNotFoundException;
import com.drcp.repository.DisasterRepository;
import com.drcp.repository.ReliefDistributionRepository;
import com.drcp.repository.ReliefRequestRepository;
import com.drcp.repository.ResourceRepository;
import com.drcp.repository.UserRepository;
import com.drcp.repository.VolunteerRepository;
import com.drcp.service.interfaces.ReliefDistributionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReliefDistributionServiceImpl
        implements ReliefDistributionService {

    private final ReliefDistributionRepository distributionRepository;

    private final ReliefRequestRepository reliefRequestRepository;

    private final ResourceRepository resourceRepository;

    private final VolunteerRepository volunteerRepository;

    private final DisasterRepository disasterRepository;

    private final UserRepository userRepository;

    @Override
    public ReliefDistributionResponse createDistribution(
            ReliefDistributionRequest request) {

        ReliefRequest reliefRequest =
                reliefRequestRepository.findById(request.getReliefRequestId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Relief request not found"));

        Resource resource =
                resourceRepository.findById(request.getResourceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Resource not found"));

        Volunteer volunteer =
                volunteerRepository.findById(request.getVolunteerId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Volunteer not found"));

        Disaster disaster =
                disasterRepository.findById(request.getDisasterId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Disaster not found"));

        if (resource.getQuantity() < request.getDistributedQuantity()) {

            throw new IllegalArgumentException(
                    "Insufficient resource quantity"
            );
        }

        resource.setQuantity(
                resource.getQuantity()
                        - request.getDistributedQuantity()
        );

        resourceRepository.save(resource);

        reliefRequest.setStatus(
                ReliefRequestStatus.COMPLETED
        );

        reliefRequestRepository.save(reliefRequest);

        volunteer.setStatus(
                VolunteerStatus.BUSY
        );

        volunteer.setAvailable(false);

        volunteerRepository.save(volunteer);

        ReliefDistribution distribution =
                ReliefDistribution.builder()

                        .reliefRequest(reliefRequest)

                        .resource(resource)

                        .volunteer(volunteer)

                        .disaster(disaster)

                        .distributedQuantity(
                                request.getDistributedQuantity()
                        )

                        .status(DistributionStatus.DELIVERED)

                        .build();

        ReliefDistribution saved =
                distributionRepository.save(distribution);

        return mapToResponse(saved);
    }

    @Override
    public List<ReliefDistributionResponse> getAllDistributions() {

        return distributionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }



    @Override
    public ReliefDistributionResponse getDistributionById(Long id) {

        ReliefDistribution distribution =
                distributionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Distribution not found"));

        return mapToResponse(distribution);
    }

    @Override
    public ReliefDistributionResponse updateDistribution(
            Long id,
            ReliefDistributionRequest request) {

        ReliefDistribution distribution =
                distributionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Distribution not found"));

        ReliefRequest reliefRequest =
                reliefRequestRepository.findById(request.getReliefRequestId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Relief request not found"));

        Resource resource =
                resourceRepository.findById(request.getResourceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Resource not found"));

        Volunteer volunteer =
                volunteerRepository.findById(request.getVolunteerId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Volunteer not found"));

        Disaster disaster =
                disasterRepository.findById(request.getDisasterId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Disaster not found"));

        distribution.setReliefRequest(reliefRequest);
        distribution.setResource(resource);
        distribution.setVolunteer(volunteer);
        distribution.setDisaster(disaster);
        distribution.setDistributedQuantity(
                request.getDistributedQuantity()
        );

        ReliefDistribution updated =
                distributionRepository.save(distribution);

        return mapToResponse(updated);
    }

    @Override
    public void deleteDistribution(Long id) {

        ReliefDistribution distribution =
                distributionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Distribution not found"));

        distributionRepository.delete(distribution);
    }

    private ReliefDistributionResponse mapToResponse(
            ReliefDistribution distribution) {

        return ReliefDistributionResponse.builder()

                .id(distribution.getId())

                .reliefRequestId(
                        distribution.getReliefRequest().getId()
                )

                .reliefTitle(
                        distribution.getReliefRequest().getTitle()
                )

                .resourceId(
                        distribution.getResource().getId()
                )

                .resourceName(
                        distribution.getResource().getResourceName()
                )

                .volunteerId(
                        distribution.getVolunteer().getId()
                )

                .volunteerName(
                        distribution.getVolunteer()
                                .getUser()
                                .getFirstName()
                                + " " +
                                distribution.getVolunteer()
                                        .getUser()
                                        .getLastName()
                )

                .disasterId(
                        distribution.getDisaster().getId()
                )

                .disasterTitle(
                        distribution.getDisaster().getTitle()
                )

                .distributedQuantity(
                        distribution.getDistributedQuantity()
                )

                .status(
                        distribution.getStatus()
                )

                .build();
    }

}