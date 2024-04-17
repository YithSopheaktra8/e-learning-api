package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;

import java.util.List;

public interface InstructorService {

    InstructorResponse createInstructor(InstructorCreateRequest instructorCreateRequest);

    List<InstructorResponse> findAllInstructors(int page, int size);
    InstructorResponse updateInstructor(String username, InstructorCreateRequest instructorCreateRequest);
    InstructorResponse findInstructorByUsername(String username);
}
