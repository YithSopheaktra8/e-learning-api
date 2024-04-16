package co.istad.elearningapi.features.enrollment.dto;


import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record EnrollmentCreateRequest(
        @NotBlank
        @Size(max = 999999)
        String code

) {
}
