package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course fromCourseCreateRequest(CourseCreateRequest courseCreateRequest);

}
