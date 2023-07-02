package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.honey.reservationadmin.dto.ReservationStatus;

public record ReservationStatusRequest(
        @JsonProperty("reservationStatus") ReservationStatus reservationStatus
) {
    public static ReservationStatusRequest of(ReservationStatus reservationStatus) {
        return new ReservationStatusRequest(reservationStatus);
    }
}
