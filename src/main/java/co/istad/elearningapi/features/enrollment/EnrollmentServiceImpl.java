package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.course.CourseRepository;
import co.istad.elearningapi.features.enrollment.dto.*;
import co.istad.elearningapi.features.student.StudentRepository;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.mapper.EnrollmentMapper;
import jakarta.transaction.Transactional;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public void createNewEnroll(EnrollmentCreateRequest enrollmentCreateRequest) {

        Course course = courseRepository.findByTitle(enrollmentCreateRequest.course())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course has not been found!"
                ));

        User user = userRepository.findByUserName(enrollmentCreateRequest.studentUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student does not exist"
                ));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student does not exist"
                ));

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setCode(UUID.randomUUID().toString());
        enrollment.setStudents(List.of(student));
        enrollment.setCertifiedAt(null);
        enrollment.setIsCertified(false);
        enrollment.setIsDeleted(false);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setProgress(0);

        enrollmentRepository.save(enrollment);
    }

    @Override
    public Page<EnrollmentResponse> findAllEnrollment(int page, int size, String sortOrder) {
        //validate page and size >= 0
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be greater than or equal to zero");
        }
        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be greater than or equal to one");
        }

        Sort sortByEnrollAt = Sort.by(Sort.Direction.ASC,"enrolledAt");
        PageRequest pageRequest = PageRequest.of(page, size,sortByEnrollAt);
        Page<Enrollment> enrollments = enrollmentRepository.findAll(pageRequest);
        return enrollments.map(enrollmentMapper::toEnrollmentResponse);
    }

    @Override
    public EnrollmentProgressResponse findEnrollmentProgress(String code) {
        if (!enrollmentRepository.existsByCode(code)) {
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
        if (!enrollmentRepository.existsByCode(code)) {
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
        if (!enrollmentRepository.existsByCode(code)) {
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

        if (!isCompleted) {
            return new BasedMessage("Student has been not complete course yet!");
        }
        return new BasedMessage("Student has been successfully completed course!");
    }

    @Transactional
    @Override
    public BasedMessage disableEnrollment(String code) {
        //  check code if exists
        if (!enrollmentRepository.existsByCode(code)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "This student has not been found!"
            );
        }
        enrollmentRepository.disableByCode(code);
        return new BasedMessage("This enrollment has been disabled!");
    }

    @Override
    public EnrollmentDetailsResponse findDetailEnrollment(String code) {

        Enrollment enrollment = enrollmentRepository.findByCode(code);

        return enrollmentMapper.toEnrollmentDetailResponse(enrollment);
    }

}
