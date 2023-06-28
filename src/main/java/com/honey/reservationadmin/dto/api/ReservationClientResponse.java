package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.honey.reservationadmin.dto.ReservationDto;

import java.util.List;

public record ReservationClientResponse(
    @JsonProperty("content") List<ReservationDto> reservationDtos,
    @JsonProperty("pageable") Pageable pageable,
    @JsonProperty("totalPages") int totalPages,
    @JsonProperty("totalElements") int totalElements
) {
    public record Pageable(int pageNumber, int pageSize) {}
}
