package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        CourseMapper.class,
        StudentMapper.class
})
public interface EnrollmentMapper {

    @Mapping(source = "course", target = "course")
    @Mapping(source = "students", target = "student")
    EnrollmentDetailsResponse toEnrollmentDetailResponse(Enrollment enrollment);

    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);

    EnrollmentProgressResponse toEnrollmentProgressResponse(Enrollment enrollment);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(EnrollmentProgressRequest enrollmentProgressRequest, @MappingTarget Enrollment enrollment);
}
