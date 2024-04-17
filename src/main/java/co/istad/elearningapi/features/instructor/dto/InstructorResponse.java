package co.istad.elearningapi.features.instructor.dto;

import co.istad.elearningapi.features.user.dto.UserSnippetResponse;

public record InstructorResponse(
        String username,
        String biography,
        String givenName,
        String jobTitle,
        String nationalIdCard,
        String familyName,
        String github,
        String linkedIn,
        String profile,
        String website
) {
}
