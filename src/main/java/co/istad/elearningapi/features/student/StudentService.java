package co.istad.elearningapi.features.student;

import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    void createStudent(StudentCreateRequest studentCreateRequest);

    Page<CategoryResponse> findAllCategory(int page, int size);


}
