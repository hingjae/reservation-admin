package com.honey.reservationadmin.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.honey.reservationadmin.dto.UserAccountDto;

import java.util.List;

public record UserAccountPageClientResponse(
        @JsonProperty("content")List<UserAccountDto> users,
        @JsonProperty("pageable") Pageable pageable,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("totalElements") int totalElements
) {
    public record Pageable(int pageNumber, int pageSize) {}
}
