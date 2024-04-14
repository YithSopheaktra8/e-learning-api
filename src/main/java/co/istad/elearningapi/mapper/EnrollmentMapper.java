package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    Enrollment fromEnrollCreateRequest(EnrollmentCreateRequest enrollmentCreateRequest);

    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
}
