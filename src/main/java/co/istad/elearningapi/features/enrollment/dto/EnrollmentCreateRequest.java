package co.istad.elearningapi.features.enrollment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record EnrollmentCreateRequest(

        @NotBlank
        String course,

        @NotBlank
        String studentUsername
) {
}
