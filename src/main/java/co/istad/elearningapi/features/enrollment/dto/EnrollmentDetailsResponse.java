package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.student.dto.StudentResponse;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentDetailsResponse(
        String code,
        Integer progress,
        LocalDateTime enrolledAt,
        Boolean isCertified,
        Boolean isDeleted,
        LocalDateTime certifiedAt,
        CourseDetailsResponse course,
        List<StudentResponse> student
) {
}
