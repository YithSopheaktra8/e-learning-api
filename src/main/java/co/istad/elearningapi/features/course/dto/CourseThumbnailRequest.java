package co.istad.elearningapi.features.course.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseThumbnailRequest(
        @NotBlank(message = "Course thumbnail is required")
        String thumbnail
) {
}
