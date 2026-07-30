package com.drcp.service.interfaces;

import com.drcp.dto.request.ResourceRequest;
import com.drcp.dto.response.ResourceResponse;
import com.drcp.entity.enums.ResourceType;

import java.util.List;

public interface ResourceService {

    ResourceResponse createResource(ResourceRequest request);

    List<ResourceResponse> getAllResources();

    ResourceResponse getResourceById(Long id);

    ResourceResponse updateResource(Long id, ResourceRequest request);

    void deleteResource(Long id);

    List<ResourceResponse> getResourcesByType(ResourceType type);

}