package com.drcp.service.impl;

import com.drcp.dto.request.ResourceRequest;
import com.drcp.dto.response.ResourceResponse;
import com.drcp.entity.Resource;
import com.drcp.entity.enums.ResourceType;
import com.drcp.exception.ResourceNotFoundException;
import com.drcp.repository.ResourceRepository;
import com.drcp.service.interfaces.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResourceResponse createResource(ResourceRequest request) {

        Resource resource = Resource.builder()
                .type(request.getType())
                .resourceName(request.getResourceName())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .warehouseLocation(request.getWarehouseLocation())
                .remarks(request.getRemarks())
                .build();

        return mapToResponse(resourceRepository.save(resource));
    }

    @Override
    public List<ResourceResponse> getAllResources() {

        return resourceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ResourceResponse getResourceById(Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found"));

        return mapToResponse(resource);
    }

    @Override
    public ResourceResponse updateResource(Long id,
                                           ResourceRequest request) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found"));

        resource.setType(request.getType());
        resource.setResourceName(request.getResourceName());
        resource.setQuantity(request.getQuantity());
        resource.setUnit(request.getUnit());
        resource.setWarehouseLocation(request.getWarehouseLocation());
        resource.setRemarks(request.getRemarks());

        return mapToResponse(resourceRepository.save(resource));
    }

    @Override
    public void deleteResource(Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found"));

        resourceRepository.delete(resource);
    }

    @Override
    public List<ResourceResponse> getResourcesByType(ResourceType type) {

        return resourceRepository.findByType(type)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ResourceResponse mapToResponse(Resource resource) {

        return ResourceResponse.builder()
                .id(resource.getId())
                .type(resource.getType())
                .resourceName(resource.getResourceName())
                .quantity(resource.getQuantity())
                .unit(resource.getUnit())
                .warehouseLocation(resource.getWarehouseLocation())
                .remarks(resource.getRemarks())
                .build();
    }
}