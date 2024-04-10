package co.istad.elearningapi.features.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseUpdateRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Alias is required")
        String alias,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "The is isFree field is required")
        Boolean isFree,

        @NotBlank(message = "Thumbnail is required")
        String thumbnail,

        @NotBlank(message = "Category alias is required")
        String categoryAlias
) {
}
