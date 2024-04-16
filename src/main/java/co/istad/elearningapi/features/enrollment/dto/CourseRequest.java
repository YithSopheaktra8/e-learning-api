package co.istad.elearningapi.features.enrollment.dto;

import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotNull
        Long id
) {
}
