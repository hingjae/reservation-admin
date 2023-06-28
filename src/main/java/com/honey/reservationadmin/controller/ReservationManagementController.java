package com.honey.reservationadmin.controller;

import com.honey.reservationadmin.dto.api.ReservationClientResponse;
import com.honey.reservationadmin.service.ReservationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/management/reservations")
@Controller
public class ReservationManagementController {

    private final ReservationManagementService reservationManagementService;

    @GetMapping
    public String  reservations(
            @PageableDefault(size = 10, page = 0) Pageable pageable, ModelMap map
    ) {
        ReservationClientResponse reservations = reservationManagementService.getReservations(pageable);
        map.addAttribute("reservations", reservations.reservationDtos());
        return "management/reservations";
    }
}
