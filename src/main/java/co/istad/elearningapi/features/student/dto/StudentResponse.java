package co.istad.elearningapi.features.student.dto;

import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserSnippetResponse;

public record StudentResponse(
        UserSnippetResponse student,
        String university,
        String highSchool
) {
}
