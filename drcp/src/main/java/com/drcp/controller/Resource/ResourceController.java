package com.drcp.controller.Resource;

import com.drcp.dto.request.ResourceRequest;
import com.drcp.dto.response.ResourceResponse;
import com.drcp.entity.enums.ResourceType;
import com.drcp.payload.ApiResponse;
import com.drcp.service.interfaces.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResourceResponse>> create(
            @Valid @RequestBody ResourceRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Resource created successfully",
                        resourceService.createResource(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResourceResponse>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        resourceService.getAllResources()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        resourceService.getResourceById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ResourceRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Resource updated successfully",
                        resourceService.updateResource(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable Long id) {

        resourceService.deleteResource(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Resource deleted successfully",
                        "Success"
                )
        );
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<ResourceResponse>>> getByType(
            @PathVariable ResourceType type) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Success",
                        resourceService.getResourcesByType(type)
                )
        );
    }
}