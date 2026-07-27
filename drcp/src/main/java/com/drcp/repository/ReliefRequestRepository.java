package com.drcp.repository;

import com.drcp.entity.ReliefRequest;
import com.drcp.entity.enums.PriorityLevel;
import com.drcp.entity.enums.ReliefRequestStatus;
import com.drcp.entity.enums.ReliefType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReliefRequestRepository extends JpaRepository<ReliefRequest, Long> {

    List<ReliefRequest> findByRequestedById(Long userId);

    List<ReliefRequest> findByDisasterId(Long disasterId);

    List<ReliefRequest> findByStatus(ReliefRequestStatus status);

    List<ReliefRequest> findByPriority(PriorityLevel priority);

    List<ReliefRequest> findByReliefType(ReliefType reliefType);

}