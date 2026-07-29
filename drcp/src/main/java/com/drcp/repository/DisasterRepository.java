package com.drcp.repository;

import com.drcp.entity.Disaster;
import com.drcp.entity.enums.DisasterStatus;
import com.drcp.entity.enums.DisasterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisasterRepository extends JpaRepository<Disaster, Long> {

    List<Disaster> findByType(DisasterType type);

    List<Disaster> findByStatus(DisasterStatus status);

    List<Disaster> findByDistrictIgnoreCase(String district);

}