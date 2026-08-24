package com.drcp.repository;

import com.drcp.entity.Donation;
import com.drcp.entity.enums.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByStatus(DonationStatus status);

    List<Donation> findByDonorId(Long donorId);

    List<Donation> findByDisasterId(Long disasterId);

    @Query("SELECT COALESCE(SUM(d.quantity), 0) FROM Donation d")
    Integer getTotalDonationQuantity();

    @Query("SELECT COALESCE(SUM(d.estimatedValue), 0) FROM Donation d")
    Double getTotalDonationValue();

}