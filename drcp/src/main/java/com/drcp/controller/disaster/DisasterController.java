package com.drcp.controller.disaster;

import com.drcp.dto.request.DisasterRequest;
import com.drcp.dto.response.DisasterResponse;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.DisasterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disasters")
@RequiredArgsConstructor
public class DisasterController {

    private final DisasterService disasterService;

    @PostMapping
    public ResponseEntity<ApiResponse<DisasterResponse>> createDisaster(
            @Valid @RequestBody DisasterRequest request) {

        DisasterResponse response = disasterService.createDisaster(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Disaster created successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DisasterResponse>>> getAllDisasters() {

        List<DisasterResponse> response = disasterService.getAllDisasters();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Disasters fetched successfully",
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DisasterResponse>> getDisasterById(
            @PathVariable Long id) {

        DisasterResponse response = disasterService.getDisasterById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Disaster fetched successfully",
                        response
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DisasterResponse>> updateDisaster(
            @PathVariable Long id,
            @Valid @RequestBody DisasterRequest request) {

        DisasterResponse response =
                disasterService.updateDisaster(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Disaster updated successfully",
                        response
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDisaster(
            @PathVariable Long id) {

        disasterService.deleteDisaster(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Disaster deleted successfully",
                        "Deleted"
                )
        );
    }

}