package com.drcp.service.interfaces;

import com.drcp.dto.request.VolunteerRequest;
import com.drcp.dto.response.VolunteerResponse;

import java.util.List;

public interface VolunteerService {

    VolunteerResponse registerVolunteer(VolunteerRequest request);

    List<VolunteerResponse> getAllVolunteers();

    VolunteerResponse getVolunteerById(Long id);

    VolunteerResponse updateVolunteer(Long id, VolunteerRequest request);

    void deleteVolunteer(Long id);

    List<VolunteerResponse> getAvailableVolunteers();

}