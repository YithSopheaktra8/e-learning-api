package co.istad.elearningapi.features.instructor.dto;

public record InstructorCreateRequest(
        String givenName,
        String jobTitle,
        String nationalIdCard,
        String biography,
        String familyName,
        String github,
        String linkedIn,
        String profile,
        String website,
        Long userId
) {
}
