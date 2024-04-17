package co.istad.elearningapi.features.enrollment.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public record EnrollmentProgressResponse(
        @Max(100)
        Integer progress
) {
}
