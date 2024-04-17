package co.istad.elearningapi.features.enrollment.dto;

import jakarta.validation.constraints.Max;

public record EnrollmentProgressRequest(
        @Max(100)
        Integer progress
) {
}
