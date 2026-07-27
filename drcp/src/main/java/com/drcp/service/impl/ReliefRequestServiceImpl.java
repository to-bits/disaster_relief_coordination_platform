package com.drcp.service.impl;

import com.drcp.dto.request.ReliefRequestRequest;
import com.drcp.dto.response.ReliefRequestResponse;
import com.drcp.entity.Disaster;
import com.drcp.entity.ReliefRequest;
import com.drcp.entity.User;
import com.drcp.repository.DisasterRepository;
import com.drcp.repository.ReliefRequestRepository;
import com.drcp.repository.UserRepository;
import com.drcp.service.interfaces.ReliefRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReliefRequestServiceImpl implements ReliefRequestService {

    private final ReliefRequestRepository reliefRequestRepository;
    private final DisasterRepository disasterRepository;
    private final UserRepository userRepository;

    @Override
    public ReliefRequestResponse createReliefRequest(ReliefRequestRequest request) {

        Disaster disaster = disasterRepository.findById(request.getDisasterId())
                .orElseThrow(() -> new RuntimeException("Disaster not found"));

        // Temporary
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReliefRequest reliefRequest = ReliefRequest.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .reliefType(request.getReliefType())
                .priority(request.getPriority())
                .quantity(request.getQuantity())
                .contactPhone(request.getContactPhone())
                .deliveryAddress(request.getDeliveryAddress())
                .requestedBy(user)
                .disaster(disaster)
                .build();

        return mapToResponse(reliefRequestRepository.save(reliefRequest));
    }

    @Override
    public List<ReliefRequestResponse> getAllReliefRequests() {

        return reliefRequestRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public ReliefRequestResponse getReliefRequestById(Long id) {

        ReliefRequest request = reliefRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relief Request not found"));

        return mapToResponse(request);

    }

    @Override
    public ReliefRequestResponse updateReliefRequest(Long id, ReliefRequestRequest dto) {

        ReliefRequest request = reliefRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relief Request not found"));

        Disaster disaster = disasterRepository.findById(dto.getDisasterId())
                .orElseThrow(() -> new RuntimeException("Disaster not found"));

        request.setTitle(dto.getTitle());
        request.setDescription(dto.getDescription());
        request.setReliefType(dto.getReliefType());
        request.setPriority(dto.getPriority());
        request.setQuantity(dto.getQuantity());
        request.setContactPhone(dto.getContactPhone());
        request.setDeliveryAddress(dto.getDeliveryAddress());
        request.setDisaster(disaster);

        return mapToResponse(reliefRequestRepository.save(request));
    }

    @Override
    public void deleteReliefRequest(Long id) {

        reliefRequestRepository.deleteById(id);

    }

    private ReliefRequestResponse mapToResponse(ReliefRequest request) {

        return ReliefRequestResponse.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .reliefType(request.getReliefType())
                .priority(request.getPriority())
                .status(request.getStatus())
                .quantity(request.getQuantity())
                .contactPhone(request.getContactPhone())
                .deliveryAddress(request.getDeliveryAddress())
                .disasterId(request.getDisaster().getId())
                .disasterTitle(request.getDisaster().getTitle())
                .requestedById(request.getRequestedBy().getId())
                .requestedByName(
                        request.getRequestedBy().getFirstName() + " "
                                + request.getRequestedBy().getLastName()
                )
                .createdAt(request.getCreatedAt())
                .build();
    }
}