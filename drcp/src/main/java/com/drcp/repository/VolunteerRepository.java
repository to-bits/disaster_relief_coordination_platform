package com.drcp.repository;

import com.drcp.entity.Volunteer;
import com.drcp.entity.enums.VolunteerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Optional<Volunteer> findByUserId(Long userId);

    List<Volunteer> findByStatus(VolunteerStatus status);

    List<Volunteer> findByAvailableTrue();

}