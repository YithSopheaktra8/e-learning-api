package co.istad.elearningapi.features.course.dto;

public record CourseResponse(
        String title,
        String alias,
        String description,
        String thumbnail
) {
}
