package com.honey.reservationadmin.controller;

import com.honey.reservationadmin.dto.api.ReservationPageClientResponse;
import com.honey.reservationadmin.service.ManagerAccountManagementService;
import com.honey.reservationadmin.service.PaginationService;
import com.honey.reservationadmin.service.ReservationManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/management/reservations")
@Controller
public class ReservationManagementController {

    private final ReservationManagementService reservationManagementService;
    private final ManagerAccountManagementService managerAccountManagementService;
    private final PaginationService paginationService;
    private final HttpServletRequest request;

    @GetMapping
    public String reservations(
            @PageableDefault(size = 20, page = 0) Pageable pageable, ModelMap map
    ) {
        ReservationPageClientResponse reservations = reservationManagementService.getReservations(pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), reservations.totalPages());

        map.addAttribute("reservations", reservations.reservationDtos());
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "management/reservations";
    }

    @GetMapping("/{reservationId}")
    public String reservation(@PathVariable("reservationId")Long reservationId, ModelMap map) {
        map.addAttribute("managers", managerAccountManagementService.getManagers());
        map.addAttribute("reservationId", reservationId);
        return "management/calendar";
    }

    @GetMapping("/{reservationId}/date-search")
    public String reservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestParam("managerId") Long managerId,
            @RequestParam("reservationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
            ModelMap map
    ) {
        map.addAttribute("date", reservationDate);
        map.addAttribute("managerId", managerId);
        map.addAttribute("timeButtons", reservationManagementService.getAvailableTime(managerId, reservationDate).timeButtons());
        map.addAttribute("reservationId", reservationId);
        return "management/reservation-form";
    }

    @PostMapping("/{reservationId}/date-search")
    public String updateReservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestParam("managerId") Long managerId,
            int year, int month, int day, String reservationTime
    ) {
        reservationManagementService.updateReservation(
                reservationId, managerId, LocalDate.of(year, month, day),
                LocalTime.parse(reservationTime, DateTimeFormatter.ofPattern("HH:mm"))
        );
        return "redirect:/management/search-date?reservationDate="+LocalDate.of(year,month,day);
    }

    @GetMapping("/{reservationId}/delete")
    public String deleteReservation(@PathVariable("reservationId") Long reservationId) {
        reservationManagementService.deleteReservation(reservationId);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{reservationId}/complete")
    public String completeReservation(@PathVariable("reservationId")Long reservationId) {
        reservationManagementService.updateReservationStatus(reservationId);
        String previousUrl = request.getHeader("Referer");
        return "redirect:" + previousUrl;
    }

}
