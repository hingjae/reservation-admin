package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReservationMemoClientResponse(@JsonProperty("memo") String memo) {
}
