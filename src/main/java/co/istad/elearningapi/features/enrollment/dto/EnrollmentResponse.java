package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentResponse(

        String code,
        Integer progress,
        LocalDateTime enrolledAt,
        Boolean isCertified,
        Boolean isDeleted,
        LocalDateTime certifiedAt
) {
}
