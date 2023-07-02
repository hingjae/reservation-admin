package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.honey.reservationadmin.dto.ReservationDto;

import java.util.List;

public record ReservationListClientResponse(
        @JsonProperty("reservations") List<ReservationDto> reservationDtos
) {

}
