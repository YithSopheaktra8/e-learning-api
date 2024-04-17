package co.istad.elearningapi.features.instructor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InstructorCreateRequest(
        @NotBlank(message = "Given name is required")
        String givenName,

        @NotBlank(message = "Job title is required")
        String jobTitle,

        @NotBlank(message = "National id card is required")
        String nationalIdCard,

        @NotBlank(message = "Biography is required")
        String biography,

        @NotBlank(message = "Family name is required")
        String familyName,

        String github,

        String linkedIn,

        @NotBlank(message = "Profile is required")
        String profile,

        String website,

        @NotBlank(message = "Address is required")
        String address1,

        String address2,

        @NotBlank(message = "Email is required")
        String email,

        @NotNull(message = "dob is required")
        LocalDate dob,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotBlank(message = "given name is required")
        String phoneNumber,

        @NotBlank(message = "Username is required")
        String username,

        String country,

        String city
) {
}
