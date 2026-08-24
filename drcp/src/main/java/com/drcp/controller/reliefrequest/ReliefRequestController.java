package com.drcp.controller.reliefrequest;

import com.drcp.dto.request.ReliefRequestRequest;
import com.drcp.dto.response.ReliefRequestResponse;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.ReliefRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relief-requests")
@RequiredArgsConstructor
public class ReliefRequestController {

    private final ReliefRequestService reliefRequestService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReliefRequestResponse>> create(
            @Valid @RequestBody ReliefRequestRequest request) {

        ReliefRequestResponse response =
                reliefRequestService.createReliefRequest(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,
                        "Relief request created successfully",
                        response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReliefRequestResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Success",
                        reliefRequestService.getAllReliefRequests())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReliefRequestResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Success",
                        reliefRequestService.getReliefRequestById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReliefRequestResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ReliefRequestRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Updated successfully",
                        reliefRequestService.updateReliefRequest(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable Long id) {

        reliefRequestService.deleteReliefRequest(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Deleted successfully",
                        "Success"));
    }
}