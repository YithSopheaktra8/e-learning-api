package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewEnroll(@Valid @RequestBody EnrollmentCreateRequest enrollmentCreateRequest, Long courseId){
        enrollmentService.createNewEnroll(enrollmentCreateRequest, courseId);
    }

    @GetMapping
    public Page<EnrollmentResponse> findAllEnrollment(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            String sortOrder
    ){
        return enrollmentService.findAllEnrollment(page, size, sortOrder);
    }

    @GetMapping("/{code}/progress")
    public EnrollmentProgressResponse findEnrollmentProgress(@PathVariable String code){
        return enrollmentService.findEnrollmentProgress(code);
    }

    @PatchMapping("/{code}/progress")
    EnrollmentResponse updateProgressByCode(@PathVariable String code,
                              @RequestBody EnrollmentUpdateRequest enrollmentUpdateRequest) {
        return enrollmentService.updateProgressByCode(code, enrollmentUpdateRequest);
    }

}
