package com.honey.reservationadmin.service;

import com.honey.reservationadmin.dto.ProjectProperties;
import com.honey.reservationadmin.dto.api.ReservationClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationManagementService {

    private final RestTemplate restTemplate;
    private final ProjectProperties projectProperties;

    public ReservationClientResponse getReservations(Pageable pageable) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations")
                .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber());

        Sort sort = pageable.getSort();
        if (sort != null) {
            for (Sort.Order order : sort) {
                uriBuilder = uriBuilder.queryParam("sort", order.getProperty() + "," + order.getDirection());
            }
        }

        return restTemplate.getForObject(uriBuilder.build().toUri(), ReservationClientResponse.class);
    }
}
