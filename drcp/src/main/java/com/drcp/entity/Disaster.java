package com.drcp.entity;

import com.drcp.entity.DisasterStatus;
import com.drcp.entity.DisasterType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "disasters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DisasterType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private DisasterStatus status = DisasterStatus.REPORTED;

    @Column(nullable = false)
    private String district;

    private String upazila;

    private Double latitude;

    private Double longitude;

    @Builder.Default
    private Integer affectedPeople = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by")
    private User reportedBy;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}