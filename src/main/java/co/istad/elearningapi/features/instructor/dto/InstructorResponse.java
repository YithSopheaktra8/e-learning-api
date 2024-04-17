package co.istad.elearningapi.features.instructor.dto;

public record InstructorResponse(
        Integer id,
        String biography,
        String givenName,
        String jobTitle,
        String nationalIdCard,
        String familyName,
        String github,
        String linkedIn,
        String profile,
        String website,
        Long userId
       // UserDetailsResponse user
) {
}
