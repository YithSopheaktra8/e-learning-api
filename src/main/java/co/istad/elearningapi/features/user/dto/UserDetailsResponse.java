package co.istad.elearningapi.features.user.dto;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.domain.Role;

import java.time.LocalDate;
import java.util.List;

public record UserDetailsResponse(
        String uuid,
        String userName,
        String profile,
        LocalDate dob,
        Country country,
        List<Role> roleList
) {
}
