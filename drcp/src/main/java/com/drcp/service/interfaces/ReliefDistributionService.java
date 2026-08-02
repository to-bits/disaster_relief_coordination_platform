package com.drcp.service.interfaces;

import com.drcp.dto.request.ReliefDistributionRequest;
import com.drcp.dto.response.ReliefDistributionResponse;

import java.util.List;

public interface ReliefDistributionService {

    ReliefDistributionResponse createDistribution(
            ReliefDistributionRequest request
    );

    List<ReliefDistributionResponse> getAllDistributions();

    ReliefDistributionResponse getDistributionById(Long id);

    ReliefDistributionResponse updateDistribution(
            Long id,
            ReliefDistributionRequest request
    );

    void deleteDistribution(Long id);

}