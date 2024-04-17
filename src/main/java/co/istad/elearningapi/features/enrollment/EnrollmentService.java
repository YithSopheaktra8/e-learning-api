package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import org.springframework.data.domain.Page;

public interface EnrollmentService {
    void createNewEnroll(EnrollmentCreateRequest enrollmentCreateRequest, Long courseId);

    Page<EnrollmentResponse> findAllEnrollment(int page, int size, String sortOrder);

    EnrollmentProgressResponse findEnrollmentProgress(String code);

    EnrollmentResponse updateProgressByCode(String code, EnrollmentUpdateRequest enrollmentUpdateRequest);

    BasedMessage updateCertification(String code);

}
