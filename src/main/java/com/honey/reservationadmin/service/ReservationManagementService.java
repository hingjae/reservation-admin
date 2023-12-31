package com.honey.reservationadmin.service;

import com.honey.reservationadmin.dto.ProjectProperties;
import com.honey.reservationadmin.dto.ReservationDto;
import com.honey.reservationadmin.dto.ReservationStatus;
import com.honey.reservationadmin.dto.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationManagementService {

    private final RestTemplate restTemplate;
    private final ProjectProperties projectProperties;

    public ReservationPageClientResponse getReservations(Pageable pageable) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations")
                .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber());

        Sort sort = pageable.getSort();
        if (sort != null) {
            for (Sort.Order order : sort) {
                uriBuilder = uriBuilder.queryParam("sort", order.getProperty() + "," + order.getDirection());
            }
        }

        return restTemplate.getForObject(uriBuilder.build().toUri(), ReservationPageClientResponse.class);
    }

    public ReservationDto getReservation(Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations/" + id)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, ReservationDto.class);
    }

    public TimeBooleanClientResponse getAvailableTime(Long managerId, LocalDate reservationDate) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations/time-search")
                .queryParam("managerId", managerId)
                .queryParam("year", reservationDate.getYear())
                .queryParam("month", reservationDate.getMonthValue())
                .queryParam("day", reservationDate.getDayOfMonth())
                .build()
                .toUri();

        return restTemplate.getForObject(uri, TimeBooleanClientResponse.class);
    }

    public void updateReservation(Long reservationId, Long managerId, LocalDate reservationDate, LocalTime reservationTime) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations/" + reservationId)
                .build()
                .toUri();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        UpdateReservationRequest request = UpdateReservationRequest.of(managerId, reservationDate, reservationTime);

        HttpEntity<UpdateReservationRequest> requestEntity = new HttpEntity<>(request, header);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, Void.class);
    }

    public void updateReservationStatus(Long reservationId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations/" + reservationId + "/complete")
                .build()
                .toUri();

        restTemplate.put(uri, ReservationStatusRequest.of(ReservationStatus.COMP));
    }

    public void deleteReservation(Long reservationId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations/" + reservationId)
                .build()
                .toUri();

        restTemplate.delete(uri);
    }


    public ReservationListClientResponse getReservationByLocalDate(LocalDate reservationDate) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/search-date")
                .queryParam("reservationDate", reservationDate)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, ReservationListClientResponse.class);
    }

    public ReservationMemoClientResponse getReservationMemo(Long reservationId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.reservation().url() + "/api/reservations/" + reservationId + "/memo")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, ReservationMemoClientResponse.class);
    }
}
