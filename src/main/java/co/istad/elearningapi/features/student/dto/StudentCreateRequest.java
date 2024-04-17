package co.istad.elearningapi.features.student.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentCreateRequest(

        String familyName,
        String givenName,
        String username,
        String password,
        String gender,
        String phoneNumber,
        String profile,
        String email,
        LocalDate dob,
        String nationalIdCard,
        String highSchool,
        String university,
        String address1,
        String address2

) {
}
