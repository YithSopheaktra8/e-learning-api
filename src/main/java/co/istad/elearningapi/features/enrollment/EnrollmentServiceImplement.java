package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImplement implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    @Override
    public void createNewEnroll(EnrollmentCreateRequest enrollmentCreateRequest, String code) {
        if (enrollmentRepository.existsByCode(code)){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "This user has been enrolled"
            );
        }
        Enrollment enrollment = enrollmentMapper.fromEnrollCreateRequest(enrollmentCreateRequest);
        enrollment.setEnrolledAt(null);
        enrollment.setCode(UUID.randomUUID().toString());
        enrollment.setCertifiedAt(null);
        enrollment.setIsCertified(false);
        enrollment.setIsDeleted(false);
        enrollment.setEnrolledAt(LocalDateTime.now());

        enrollmentRepository.save(enrollment);
    }

    @Override
    public Page<EnrollmentResponse> findAllEnrollment(int page, int size, String sortOrder) {
        //validate page and size >= 0
        if (page < 0 ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be greater than or equal to zero");
        }
        if (size < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be greater than or equal to one");
        }
        Sort sortByDate = Sort.by(sortOrder.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC, "enrolledAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortByDate);
        Page<Enrollment> enrollments = enrollmentRepository.findAll(pageRequest);
        return enrollments.map(enrollmentMapper::toEnrollmentResponse);
    }
}
