package com.drcp.controller.Volunteer;

import com.drcp.dto.request.VolunteerRequest;
import com.drcp.dto.response.VolunteerResponse;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.VolunteerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<ApiResponse<VolunteerResponse>> create(
            @Valid @RequestBody VolunteerRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Volunteer registered successfully",
                        volunteerService.registerVolunteer(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VolunteerResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        volunteerService.getAllVolunteers()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VolunteerResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        volunteerService.getVolunteerById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VolunteerResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody VolunteerRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Volunteer updated successfully",
                        volunteerService.updateVolunteer(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable Long id) {

        volunteerService.deleteVolunteer(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Volunteer deleted successfully",
                        "Success"
                )
        );
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<List<VolunteerResponse>>> available() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Available volunteers",
                        volunteerService.getAvailableVolunteers()
                )
        );
    }
}