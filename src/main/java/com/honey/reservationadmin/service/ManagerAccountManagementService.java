package com.honey.reservationadmin.service;

import com.honey.reservationadmin.dto.ManagerAccountDto;
import com.honey.reservationadmin.dto.ProjectProperties;
import com.honey.reservationadmin.dto.api.ManagerAccountListClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagerAccountManagementService {

    private final RestTemplate restTemplate;
    private final ProjectProperties projectProperties;

    public List<ManagerAccountDto> getManagers() {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/managerAccounts")
                .build()
                .toUri();

        ManagerAccountListClientResponse response = restTemplate.getForObject(uri, ManagerAccountListClientResponse.class);
        return response.managerAccountDtos();
    }
}
