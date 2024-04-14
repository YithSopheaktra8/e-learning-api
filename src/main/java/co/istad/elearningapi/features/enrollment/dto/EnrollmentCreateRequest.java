package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record EnrollmentCreateRequest(
        @NotBlank
        String code,
        Integer progress
//        @NotEmpty
//        Course course,
//        @NotEmpty
//        List<Student> students
) {
}
