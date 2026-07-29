package com.drcp.controller.donation;

import com.drcp.dto.request.DonationRequest;
import com.drcp.dto.response.DonationResponse;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<ApiResponse<DonationResponse>> create(
            @Valid @RequestBody DonationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Donation created successfully",
                        donationService.createDonation(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DonationResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        donationService.getAllDonations()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DonationResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        donationService.getDonationById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DonationResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody DonationRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Donation updated successfully",
                        donationService.updateDonation(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable Long id) {

        donationService.deleteDonation(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Donation deleted successfully",
                        "Success"
                )
        );
    }

    @GetMapping("/disaster/{disasterId}")
    public ResponseEntity<ApiResponse<List<DonationResponse>>> getByDisaster(
            @PathVariable Long disasterId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        donationService.getDonationsByDisaster(disasterId)
                )
        );
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<ApiResponse<List<DonationResponse>>> getByDonor(
            @PathVariable Long donorId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        donationService.getDonationsByDonor(donorId)
                )
        );
    }
}