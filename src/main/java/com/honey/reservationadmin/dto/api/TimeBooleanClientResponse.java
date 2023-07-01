package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Map;

public record TimeBooleanClientResponse(
        @JsonProperty("timeBooleanMap")Map<LocalTime, Boolean> timeButtons
) {
}
