package com.drcp.repository;

import com.drcp.entity.Disaster;
import com.drcp.entity.DisasterSeverity;
import com.drcp.entity.DisasterStatus;
import com.drcp.entity.DisasterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisasterRepository extends JpaRepository<Disaster, Long> {

    List<Disaster> findByStatus(DisasterStatus status);

    List<Disaster> findByDisasterType(DisasterType disasterType);

    List<Disaster> findBySeverity(DisasterSeverity severity);

}