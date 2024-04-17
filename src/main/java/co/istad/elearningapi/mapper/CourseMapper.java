package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseDetailsResponse;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course fromCourseCreateRequest(CourseCreateRequest courseCreateRequest);

    List<CourseResponse> toCourseListResponse(List<Course> courses);

    CourseResponse toCourseResponse(Course course);


    CourseDetailsResponse toCourseDetailResponse(Course course);

}
