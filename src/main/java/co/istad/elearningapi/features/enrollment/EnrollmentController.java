package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
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

}
