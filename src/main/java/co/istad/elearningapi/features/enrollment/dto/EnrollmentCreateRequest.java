package co.istad.elearningapi.features.enrollment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record EnrollmentCreateRequest(
        @NotBlank
        String code
//        StudentRequest student

) {
}
