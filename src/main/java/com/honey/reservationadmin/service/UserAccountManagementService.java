package com.honey.reservationadmin.service;

import com.honey.reservationadmin.dto.ProjectProperties;
import com.honey.reservationadmin.dto.api.UserAccountPageClientResponse;
import com.honey.reservationadmin.dto.api.UserReservationClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class UserAccountManagementService {

    private final RestTemplate restTemplate;
    private final ProjectProperties projectProperties;

    public UserAccountPageClientResponse getUserAccounts(Pageable pageable) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/userAccounts")
                .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber())
                .build()
                .toUri();

        return restTemplate.getForObject(uri, UserAccountPageClientResponse.class);
    }

    public void deleteUserAccount(String userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/userAccounts/" + userId)
                .build()
                .toUri();

        restTemplate.delete(uri);
    }


    public UserReservationClientResponse getUserReservations(String userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/userAccounts/" + userId)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, UserReservationClientResponse.class);
    }
}
