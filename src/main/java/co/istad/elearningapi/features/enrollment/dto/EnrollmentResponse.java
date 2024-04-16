package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentResponse(
        @NotBlank
        String code,
        Integer progress,
        LocalDateTime enrolledAt,
        Boolean isCertified,
        Boolean isDeleted,
        LocalDateTime certifiedAt,
        @NotEmpty
        Course course
//        @NotEmpty
//        List<Student> students
) {
}
