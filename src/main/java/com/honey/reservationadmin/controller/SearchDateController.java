package com.honey.reservationadmin.controller;

import com.honey.reservationadmin.service.ReservationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/management/search-date")
@Controller
public class SearchDateController {

    private final ReservationManagementService reservationManagementService;

    @GetMapping
    public String getReservation(
            @RequestParam(value = "reservationDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
            ModelMap map
    ) {
        if (reservationDate == null) {reservationDate = LocalDate.now();}

        map.addAttribute("date", reservationDate);
        map.addAttribute("reservations", reservationManagementService.getReservationByLocalDate(reservationDate).reservationDtos());
        return "management/search-date";
    }

}
