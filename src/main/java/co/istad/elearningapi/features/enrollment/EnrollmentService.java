package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.enrollment.dto.*;
import org.springframework.data.domain.Page;

public interface EnrollmentService {
    void createNewEnroll(EnrollmentCreateRequest enrollmentCreateRequest);

    Page<EnrollmentResponse> findAllEnrollment(int page, int size, String sortOrder);

    EnrollmentProgressResponse findEnrollmentProgress(String code);

    EnrollmentResponse updateProgressByCode(String code, EnrollmentProgressRequest enrollmentProgressRequest);

    BasedMessage updateCertification(String code);

    BasedMessage disableEnrollment(String code);

    EnrollmentDetailsResponse findDetailEnrollment(String code);

}
