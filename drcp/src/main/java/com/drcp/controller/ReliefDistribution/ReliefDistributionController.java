package com.drcp.controller.ReliefDistribution;

import com.drcp.dto.request.ReliefDistributionRequest;
import com.drcp.dto.response.ReliefDistributionResponse;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.ReliefDistributionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributions")
@RequiredArgsConstructor
public class ReliefDistributionController {

    private final ReliefDistributionService reliefDistributionService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReliefDistributionResponse>> create(
            @Valid @RequestBody ReliefDistributionRequest request
    ) {

        ReliefDistributionResponse response =
                reliefDistributionService.createDistribution(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Relief distributed successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReliefDistributionResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        reliefDistributionService.getAllDistributions()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReliefDistributionResponse>> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        reliefDistributionService.getDistributionById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReliefDistributionResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ReliefDistributionRequest request
    ) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Distribution updated successfully",
                        reliefDistributionService.updateDistribution(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable Long id
    ) {

        reliefDistributionService.deleteDistribution(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Distribution deleted successfully",
                        "Success"
                )
        );
    }

}