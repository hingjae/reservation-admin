package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.honey.reservationadmin.dto.ReservationDto;

import java.util.List;

public record UserReservationClientResponse(
        @JsonProperty("name") String name,
        @JsonProperty("reservations") List<ReservationDto> reservations
) {

}
