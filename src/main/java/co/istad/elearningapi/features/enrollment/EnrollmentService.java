package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import org.springframework.data.domain.Page;

public interface EnrollmentService {
    void createNewEnroll(EnrollmentCreateRequest enrollmentCreateRequest, Long courseId);

    Page<EnrollmentResponse> findAllEnrollment(int page, int size, String sortOrder);

}
