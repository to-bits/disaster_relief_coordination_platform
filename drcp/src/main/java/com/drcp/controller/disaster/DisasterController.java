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
    public ResponseEntity<ApiResponse<DisasterResponse>> create(
            @Valid @RequestBody DisasterRequest request) {

        DisasterResponse response = disasterService.createDisaster(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Disaster created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DisasterResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", disasterService.getAllDisasters())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DisasterResponse>> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", disasterService.getDisasterById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DisasterResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody DisasterRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Updated successfully",
                        disasterService.updateDisaster(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {

        disasterService.deleteDisaster(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Deleted successfully", "Success")
        );
    }

}