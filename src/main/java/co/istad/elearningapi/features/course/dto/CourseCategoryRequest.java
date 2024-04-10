package co.istad.elearningapi.features.course.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseCategoryRequest(
        @NotBlank(message = "Category alias is required")
        String categoryAlias
) {
}
