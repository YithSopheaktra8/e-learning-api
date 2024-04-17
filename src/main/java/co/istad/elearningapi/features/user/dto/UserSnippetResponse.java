package co.istad.elearningapi.features.user.dto;

import co.istad.elearningapi.domain.Country;

import java.time.LocalDate;

public record UserSnippetResponse(
        String uuid,
        String userName,
        String profile,
        LocalDate dob,
        Country country
) {
}
