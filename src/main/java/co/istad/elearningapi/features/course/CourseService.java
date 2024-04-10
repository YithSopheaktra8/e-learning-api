package co.istad.elearningapi.features.course;

import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    void createNew(CourseCreateRequest courseCreateRequest);

    Page<CourseResponse> findAll(int page, int size);

}
