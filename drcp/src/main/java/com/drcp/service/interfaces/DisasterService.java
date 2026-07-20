package com.drcp.service.interfaces;

import com.drcp.dto.request.DisasterRequest;
import com.drcp.dto.response.DisasterResponse;

import java.util.List;

public interface DisasterService {

    DisasterResponse createDisaster(DisasterRequest request);

    List<DisasterResponse> getAllDisasters();

    DisasterResponse getDisasterById(Long id);

    DisasterResponse updateDisaster(Long id, DisasterRequest request);

    void deleteDisaster(Long id);

}