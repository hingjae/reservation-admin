package com.honey.reservationadmin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto(
        Long id,
        Long managerId,
        String managerName,
        String loginId,
        String username,
        LocalDate reservationDate,
        LocalTime reservationTime,
        ReservationStatus reservationStatus,
        String memo
) {

}
