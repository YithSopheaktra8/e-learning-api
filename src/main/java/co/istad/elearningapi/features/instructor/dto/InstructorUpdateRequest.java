package co.istad.elearningapi.features.instructor.dto;

public record InstructorUpdateRequest(
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
