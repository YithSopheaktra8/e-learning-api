package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.course.CourseRepository;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import co.istad.elearningapi.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImplement implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseRepository courseRepository;
    @Override
    public void createNewEnroll(EnrollmentCreateRequest enrollmentCreateRequest, Long courseId) {
        if (enrollmentRepository.existsByCode(enrollmentCreateRequest.code())){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "This user has been enrolled"
            );
        }
        Enrollment enrollment = enrollmentMapper.fromEnrollCreateRequest(enrollmentCreateRequest);
        enrollment.setCertifiedAt(null);
        enrollment.setIsCertified(false);
        enrollment.setIsDeleted(false);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setProgress(0);

        // Find the course by ID and set it for the enrollment
        Course selectCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course ID has been not found!"
                ));
        enrollment.setCourse(selectCourse);  // Set a single course directly

        // set info for student (wait for student feature ready)


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

    @Override
    public EnrollmentProgressResponse findEnrollmentProgress(String code) {
        if (!enrollmentRepository.existsByCode(code)){
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "This student has been not found!"
           );
       }
        // Retrieve the enrollment using the code
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "This student has not been found!"
            );
        }

        // Map the progress of the enrollment to the response
        return enrollmentMapper.toEnrollmentProgressResponse(enrollment);
    }

    @Override
    public EnrollmentResponse updateProgressByCode(String code, EnrollmentUpdateRequest enrollmentUpdateRequest) {
    //  check code if exists
    if (!enrollmentRepository.existsByCode(code)){
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "This student has not been found!"
        );
    }
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        enrollmentMapper.fromUserUpdateRequest(enrollmentUpdateRequest, enrollment);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public BasedMessage updateCertification(String code) {
        boolean isCompleted = false;
        //  check code if exists
        if (!enrollmentRepository.existsByCode(code)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "This student has not been found!"
            );
        }
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment.getProgress() == 100 && !enrollment.getIsCertified()) {
            enrollment.setIsCertified(true);
            enrollment.setCertifiedAt(LocalDate.now());
            enrollmentRepository.save(enrollment);
            isCompleted = true;
        }

        if (isCompleted == false){
           return new BasedMessage("Student has been not complete course yet!");
        }
        return new BasedMessage("Student has been successfully completed course!");
    }

}
