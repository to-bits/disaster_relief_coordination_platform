package com.drcp.service.interfaces;

import com.drcp.dto.request.DonationRequest;
import com.drcp.dto.response.DonationResponse;

import java.util.List;

public interface DonationService {

    DonationResponse createDonation(DonationRequest request);

    List<DonationResponse> getAllDonations();

    DonationResponse getDonationById(Long id);

    DonationResponse updateDonation(Long id, DonationRequest request);

    void deleteDonation(Long id);

    List<DonationResponse> getDonationsByDisaster(Long disasterId);

    List<DonationResponse> getDonationsByDonor(Long donorId);

}