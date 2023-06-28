package com.honey.reservationadmin.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("project")
public record ProjectProperties(Reservation reservation) {

    public record Reservation(String url) {}
}
