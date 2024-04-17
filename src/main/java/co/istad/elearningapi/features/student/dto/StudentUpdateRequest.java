package co.istad.elearningapi.features.student.dto;

import java.time.LocalDate;

public record StudentUpdateRequest(
        String userName,
        String password,
        String phoneNumber,
        String profile,
        String email,
        String highSchool,
        String university,
        String address1,
        String address2
) {
}
