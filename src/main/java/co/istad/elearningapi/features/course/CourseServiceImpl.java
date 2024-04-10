package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.category.CategoryRepository;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public void createNew(CourseCreateRequest courseCreateRequest) {

        Course course = courseMapper.fromCourseCreateRequest(courseCreateRequest);

        Category category = categoryRepository.findByAlias(courseCreateRequest.categoryAlias())
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found!"
                ));

        course.setCategory(category);
        course.setIsDeleted(false);
        course.setInstructor(null);

        courseRepository.save(course);

    }
}
