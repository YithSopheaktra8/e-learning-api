package co.istad.elearningapi.features.student.dto;

import co.istad.elearningapi.features.user.dto.UserDetailsResponse;

public record StudentResponse(
        UserDetailsResponse student,
        String university,
        String highsSchool
) {
}
