package com.drcp.service.impl;

import com.drcp.dto.request.VolunteerRequest;
import com.drcp.dto.response.VolunteerResponse;
import com.drcp.entity.User;
import com.drcp.entity.Volunteer;
import com.drcp.exception.ResourceNotFoundException;
import com.drcp.repository.UserRepository;
import com.drcp.repository.VolunteerRepository;
import com.drcp.service.interfaces.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;

    @Override
    public VolunteerResponse registerVolunteer(VolunteerRequest request) {

        // Temporary (JWT আসার আগে)
        User user = userRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Volunteer volunteer = Volunteer.builder()
                .user(user)
                .skills(request.getSkills())
                .currentLocation(request.getCurrentLocation())
                .build();

        return mapToResponse(
                volunteerRepository.save(volunteer)
        );
    }

    @Override
    public List<VolunteerResponse> getAllVolunteers() {

        return volunteerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public VolunteerResponse getVolunteerById(Long id) {

        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Volunteer not found"));

        return mapToResponse(volunteer);
    }

    @Override
    public VolunteerResponse updateVolunteer(Long id,
                                             VolunteerRequest request) {

        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Volunteer not found"));

        volunteer.setSkills(request.getSkills());
        volunteer.setCurrentLocation(request.getCurrentLocation());

        return mapToResponse(
                volunteerRepository.save(volunteer)
        );
    }

    @Override
    public void deleteVolunteer(Long id) {

        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Volunteer not found"));

        volunteerRepository.delete(volunteer);
    }

    @Override
    public List<VolunteerResponse> getAvailableVolunteers() {

        return volunteerRepository.findByAvailableTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private VolunteerResponse mapToResponse(Volunteer volunteer) {

        return VolunteerResponse.builder()
                .id(volunteer.getId())
                .userId(volunteer.getUser().getId())
                .fullName(
                        volunteer.getUser().getFirstName() + " "
                                + volunteer.getUser().getLastName()
                )
                .email(volunteer.getUser().getEmail())
                .skills(volunteer.getSkills())
                .currentLocation(volunteer.getCurrentLocation())
                .status(volunteer.getStatus())
                .available(volunteer.getAvailable())
                .build();
    }
}