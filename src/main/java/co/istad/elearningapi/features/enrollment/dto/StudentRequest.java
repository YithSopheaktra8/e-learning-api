package co.istad.elearningapi.features.enrollment.dto;

import jakarta.validation.constraints.Size;

public record StudentRequest(
        @Size(max = 100)
        String highSchool,
        @Size(max = 100)
        String university
) {
}
