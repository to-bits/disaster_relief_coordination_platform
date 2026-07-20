package com.drcp.service.impl;

import com.drcp.dto.request.DisasterRequest;
import com.drcp.dto.response.DisasterResponse;
import com.drcp.entity.Disaster;
import com.drcp.exception.ResourceNotFoundException;
import com.drcp.repository.DisasterRepository;
import com.drcp.service.interfaces.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisasterServiceImpl implements DisasterService {

    private final DisasterRepository disasterRepository;

    @Override
    public DisasterResponse createDisaster(DisasterRequest request) {

        Disaster disaster = Disaster.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .disasterType(request.getDisasterType())
                .severity(request.getSeverity())
                .location(request.getLocation())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        Disaster savedDisaster = disasterRepository.save(disaster);

        return mapToResponse(savedDisaster);
    }

    @Override
    public List<DisasterResponse> getAllDisasters() {

        return disasterRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DisasterResponse getDisasterById(Long id) {

        Disaster disaster = disasterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Disaster not found with id: " + id));

        return mapToResponse(disaster);
    }

    @Override
    public DisasterResponse updateDisaster(Long id, DisasterRequest request) {

        Disaster disaster = disasterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Disaster not found with id: " + id));

        disaster.setTitle(request.getTitle());
        disaster.setDescription(request.getDescription());
        disaster.setDisasterType(request.getDisasterType());
        disaster.setSeverity(request.getSeverity());
        disaster.setLocation(request.getLocation());
        disaster.setLatitude(request.getLatitude());
        disaster.setLongitude(request.getLongitude());
        disaster.setStartDate(request.getStartDate());
        disaster.setEndDate(request.getEndDate());
        disaster.setStatus(request.getStatus());
        disaster.setUpdatedAt(LocalDateTime.now());

        Disaster updatedDisaster = disasterRepository.save(disaster);

        return mapToResponse(updatedDisaster);
    }

    @Override
    public void deleteDisaster(Long id) {

        Disaster disaster = disasterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Disaster not found with id: " + id));

        disasterRepository.delete(disaster);
    }

    private DisasterResponse mapToResponse(Disaster disaster) {

        return DisasterResponse.builder()
                .id(disaster.getId())
                .title(disaster.getTitle())
                .description(disaster.getDescription())
                .disasterType(disaster.getDisasterType())
                .severity(disaster.getSeverity())
                .location(disaster.getLocation())
                .latitude(disaster.getLatitude())
                .longitude(disaster.getLongitude())
                .startDate(disaster.getStartDate())
                .endDate(disaster.getEndDate())
                .status(disaster.getStatus())
                .build();
    }
}