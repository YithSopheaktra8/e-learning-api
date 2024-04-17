package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    Enrollment fromEnrollCreateRequest(EnrollmentCreateRequest enrollmentCreateRequest);

    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
    EnrollmentProgressResponse toEnrollmentProgressResponse(Enrollment enrollment);
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)

    void fromUserUpdateRequest(EnrollmentUpdateRequest enrollmentUpdateRequest, @MappingTarget Enrollment enrollment);
}
