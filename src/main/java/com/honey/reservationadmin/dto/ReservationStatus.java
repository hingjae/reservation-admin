package com.honey.reservationadmin.dto;

import lombok.Getter;

public enum ReservationStatus {
    READY("방문 전"),
    COMP("방문 완료");

    @Getter
    private final String description;

    ReservationStatus(String description) {
        this.description = description;
    }
}
