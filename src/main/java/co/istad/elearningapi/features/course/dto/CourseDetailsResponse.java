package co.istad.elearningapi.features.course.dto;

public record CourseDetailsResponse(
        String title,
        String alias,
        String description,
        String thumbnail
) {
}
