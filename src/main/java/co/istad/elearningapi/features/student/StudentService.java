package co.istad.elearningapi.features.student;

import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    void createStudent(StudentCreateRequest studentCreateRequest);

    Page<StudentResponse> findAllStudent(int page, int size);

    StudentResponse findByUsername(String username);

    void editStudentProfile(String username, StudentUpdateRequest studentUpdateRequest);


}
