package com.drcp.entity;

import com.drcp.entity.enums.VolunteerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "volunteers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String skills;

    @Column(nullable = false)
    private String currentLocation;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VolunteerStatus status = VolunteerStatus.AVAILABLE;

    @Builder.Default
    @Column(nullable = false)
    private Boolean available = true;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @PreUpdate
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}