package com.drcp.service.impl;

import com.drcp.dto.request.DonationRequest;
import com.drcp.dto.response.DonationResponse;
import com.drcp.entity.Disaster;
import com.drcp.entity.Donation;
import com.drcp.entity.Resource;
import com.drcp.entity.User;
import com.drcp.exception.ResourceNotFoundException;
import com.drcp.repository.DisasterRepository;
import com.drcp.repository.DonationRepository;
import com.drcp.repository.ResourceRepository;
import com.drcp.repository.UserRepository;
import com.drcp.service.interfaces.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final DisasterRepository disasterRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public DonationResponse createDonation(DonationRequest request) {

        User donor = userRepository.findById(request.getDonorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Donor not found"));

        Disaster disaster = disasterRepository.findById(request.getDisasterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Disaster not found"));

        Resource resource = resourceRepository.findById(request.getResourceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found"));

        Donation donation = Donation.builder()
                .donor(donor)
                .disaster(disaster)
                .resource(resource)
                .quantity(request.getQuantity())
                .estimatedValue(request.getEstimatedValue())
                .remarks(request.getRemarks())
                .status(request.getStatus())
                .build();

        Donation savedDonation = donationRepository.save(donation);

        return mapToResponse(savedDonation);
    }

    @Override
    public List<DonationResponse> getAllDonations() {

        return donationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DonationResponse getDonationById(Long id) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Donation not found"));

        return mapToResponse(donation);
    }

    @Override
    public DonationResponse updateDonation(Long id,
                                           DonationRequest request) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Donation not found"));

        User donor = userRepository.findById(request.getDonorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Donor not found"));

        Disaster disaster = disasterRepository.findById(request.getDisasterId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Disaster not found"));

        Resource resource = resourceRepository.findById(request.getResourceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found"));

        donation.setDonor(donor);
        donation.setDisaster(disaster);
        donation.setResource(resource);
        donation.setQuantity(request.getQuantity());
        donation.setEstimatedValue(request.getEstimatedValue());
        donation.setRemarks(request.getRemarks());
        donation.setStatus(request.getStatus());

        Donation updatedDonation = donationRepository.save(donation);

        return mapToResponse(updatedDonation);
    }

    @Override
    public void deleteDonation(Long id) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Donation not found"));

        donationRepository.delete(donation);
    }

    @Override
    public List<DonationResponse> getDonationsByDisaster(Long disasterId) {

        return donationRepository.findByDisasterId(disasterId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<DonationResponse> getDonationsByDonor(Long donorId) {

        return donationRepository.findByDonorId(donorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DonationResponse mapToResponse(Donation donation) {

        return DonationResponse.builder()

                .id(donation.getId())

                .donorId(
                        donation.getDonor().getId()
                )

                .donorName(
                        donation.getDonor().getFirstName()
                                + " "
                                + donation.getDonor().getLastName()
                )

                .disasterId(
                        donation.getDisaster().getId()
                )

                .disasterTitle(
                        donation.getDisaster().getTitle()
                )

                .resourceId(
                        donation.getResource().getId()
                )

                .resourceName(
                        donation.getResource().getResourceName()
                )

                .quantity(
                        donation.getQuantity()
                )

                .estimatedValue(
                        donation.getEstimatedValue()
                )

                .status(
                        donation.getStatus()
                )

                .remarks(
                        donation.getRemarks()
                )

                .build();
    }

}