package com.honey.reservationadmin.controller;

import com.honey.reservationadmin.dto.api.UserAccountPageClientResponse;
import com.honey.reservationadmin.dto.api.UserReservationClientResponse;
import com.honey.reservationadmin.service.PaginationService;
import com.honey.reservationadmin.service.UserAccountManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/management/userAccounts")
@Controller
public class UserAccountManagementController {

    private final UserAccountManagementService userAccountManagementService;
    private final PaginationService paginationService;

    @GetMapping
    public String userAccounts(
            @PageableDefault(size = 20, page = 0) Pageable pageable, ModelMap map,
            @RequestParam(name = "searchValue", required = false) String searchValue
    ) {
        UserAccountPageClientResponse userAccounts = userAccountManagementService.getUserAccounts(pageable, searchValue);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), userAccounts.totalPages());

        map.addAttribute("users", userAccounts.users());
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "management/user-accounts";
    }

    @GetMapping("/{userId}")
    public String userReservations(@PathVariable("userId") String userId, ModelMap map) {
        UserReservationClientResponse response = userAccountManagementService.getUserReservations(userId);
        map.addAttribute("reservations", response.reservations());
        map.addAttribute("name", response.name());
        return "management/user-reservations";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable("userId") String userId) {
        userAccountManagementService.deleteUserAccount(userId);
        return "redirect:/management/userAccounts";
    }
}
