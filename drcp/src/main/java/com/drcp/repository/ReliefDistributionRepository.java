package com.drcp.repository;

import com.drcp.entity.ReliefDistribution;
import com.drcp.entity.enums.DistributionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReliefDistributionRepository
        extends JpaRepository<ReliefDistribution, Long> {

    List<ReliefDistribution> findByStatus(DistributionStatus status);

    List<ReliefDistribution> findByVolunteerId(Long volunteerId);

    List<ReliefDistribution> findByDisasterId(Long disasterId);

}