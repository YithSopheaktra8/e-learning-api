package co.istad.elearningapi.features.course;

import co.istad.elearningapi.features.course.dto.CourseCreateRequest;

public interface CourseService {

    void createNew(CourseCreateRequest courseCreateRequest);

}
