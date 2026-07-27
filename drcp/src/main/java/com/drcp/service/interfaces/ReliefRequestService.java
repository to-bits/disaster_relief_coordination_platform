package com.drcp.service.interfaces;

import com.drcp.dto.request.ReliefRequestRequest;
import com.drcp.dto.response.ReliefRequestResponse;

import java.util.List;

public interface ReliefRequestService {

    ReliefRequestResponse createReliefRequest(ReliefRequestRequest request);

    List<ReliefRequestResponse> getAllReliefRequests();

    ReliefRequestResponse getReliefRequestById(Long id);

    ReliefRequestResponse updateReliefRequest(Long id, ReliefRequestRequest request);

    void deleteReliefRequest(Long id);

}