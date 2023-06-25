package com.honey.reservationadmin.controller;

import com.honey.reservationadmin.service.ReservationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/management/reservations")
@Controller
public class ReservationManagementController {

    private final ReservationManagementService reservationManagementService;

    @GetMapping
    public String reservations() {
        return "management/reservations";
    }
}
