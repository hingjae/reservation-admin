package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.honey.reservationadmin.dto.ManagerAccountDto;

import java.util.List;

public record ManagerAccountListClientResponse(
        @JsonProperty("managers") List<ManagerAccountDto> managerAccountDtos
) {
}
