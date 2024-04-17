package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InstructorService {

    void createInstructor(InstructorCreateRequest instructorCreateRequest);

    Page<InstructorResponse> findAllInstructors(int page, int size);
    InstructorResponse updateInstructor(String username, InstructorCreateRequest instructorCreateRequest);
    InstructorResponse findInstructorByUsername(String username);
}
