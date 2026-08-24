package com.drcp.repository;

import com.drcp.entity.Resource;
import com.drcp.entity.enums.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByType(ResourceType type);

    List<Resource> findByQuantityGreaterThan(Integer quantity);


    @Query("SELECT COALESCE(SUM(r.quantity), 0) FROM Resource r")
    Integer getTotalAvailableQuantity();
}