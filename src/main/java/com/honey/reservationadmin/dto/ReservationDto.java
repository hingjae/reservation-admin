package com.honey.reservationadmin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto(
        Long id,
        Long managerId,
        String loginId,
        LocalDate reservationDate,
        LocalTime reservationTime,
        ReservationStatus reservationStatus,
        String memo
) {

}
