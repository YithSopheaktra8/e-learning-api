package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.enrollment.dto.*;
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
    void createNewEnroll(@Valid @RequestBody EnrollmentCreateRequest enrollmentCreateRequest){
        enrollmentService.createNewEnroll(enrollmentCreateRequest);
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

    @GetMapping("/{code}")
    public EnrollmentDetailsResponse findDetailEnrollment(@PathVariable String code){
        return enrollmentService.findDetailEnrollment(code);
    }

    @PatchMapping("/{code}/progress")
    EnrollmentResponse updateProgressByCode(@PathVariable String code,
                              @RequestBody EnrollmentUpdateRequest enrollmentUpdateRequest) {
        return enrollmentService.updateProgressByCode(code, enrollmentUpdateRequest);
    }

    @PutMapping("/{code}/is-certified")
    BasedMessage updateProgressByCode(@PathVariable String code) {
        return enrollmentService.updateCertification(code);
    }

    @PutMapping("/{code}")
    BasedMessage disableEnrollment(@PathVariable String code) {
        return enrollmentService.disableEnrollment(code);
    }
}
