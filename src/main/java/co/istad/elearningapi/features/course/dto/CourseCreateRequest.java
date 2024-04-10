package co.istad.elearningapi.features.course.dto;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.category.dto.CategoryResponse;

public record CourseCreateRequest(
        String title,
        String alias,
        String description,
        Boolean isFree,
        String thumbnail,
        String categoryAlias
) {
}
