package com.drcp.service.impl;

import com.drcp.dto.request.DisasterRequest;
import com.drcp.dto.response.DisasterResponse;
import com.drcp.entity.Disaster;
import com.drcp.repository.DisasterRepository;
import com.drcp.service.interfaces.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .type(request.getType())
                .district(request.getDistrict())
                .upazila(request.getUpazila())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .affectedPeople(request.getAffectedPeople())
                .build();

        return mapToResponse(disasterRepository.save(disaster));
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
                .orElseThrow(() -> new RuntimeException("Disaster not found"));

        return mapToResponse(disaster);

    }

    @Override
    public DisasterResponse updateDisaster(Long id, DisasterRequest request) {

        Disaster disaster = disasterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disaster not found"));

        disaster.setTitle(request.getTitle());
        disaster.setDescription(request.getDescription());
        disaster.setType(request.getType());
        disaster.setDistrict(request.getDistrict());
        disaster.setUpazila(request.getUpazila());
        disaster.setLatitude(request.getLatitude());
        disaster.setLongitude(request.getLongitude());
        disaster.setAffectedPeople(request.getAffectedPeople());

        return mapToResponse(disasterRepository.save(disaster));

    }

    @Override
    public void deleteDisaster(Long id) {

        disasterRepository.deleteById(id);

    }

    private DisasterResponse mapToResponse(Disaster disaster) {

        return DisasterResponse.builder()
                .id(disaster.getId())
                .title(disaster.getTitle())
                .description(disaster.getDescription())
                .type(disaster.getType())
                .status(disaster.getStatus())
                .district(disaster.getDistrict())
                .upazila(disaster.getUpazila())
                .latitude(disaster.getLatitude())
                .longitude(disaster.getLongitude())
                .affectedPeople(disaster.getAffectedPeople())
                .reportedBy(
                        disaster.getReportedBy() != null
                                ? disaster.getReportedBy().getFirstName() + " " + disaster.getReportedBy().getLastName()
                                : null
                )
                .createdAt(disaster.getCreatedAt())
                .build();

    }
}