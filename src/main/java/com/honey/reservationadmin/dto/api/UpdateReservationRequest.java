package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateReservationRequest {
    @JsonProperty("managerId")
    private Long managerId;

    @JsonProperty("reservationDate")
    private LocalDate reservationDate;

    @JsonProperty("reservationTime")
    private LocalTime reservationTime;

    private UpdateReservationRequest(Long managerId, LocalDate reservationDate, LocalTime reservationTime) {
        this.managerId = managerId;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public static UpdateReservationRequest of(Long managerId, LocalDate reservationDate, LocalTime reservationTime) {
        return new UpdateReservationRequest(managerId, reservationDate, reservationTime);
    }
}
