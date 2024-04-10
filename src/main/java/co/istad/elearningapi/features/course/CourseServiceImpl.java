package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.category.CategoryRepository;
import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import co.istad.elearningapi.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public Page<CourseResponse> findAll(int page, int size) {

        if(page < 0 ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "page number must be greater than 0"
            );
        }

        if(size < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Size must be greater than 1!"
            );
        }


        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Course> courses = courseRepository.findAllByIsDeletedFalse(pageRequest);

        if(courses.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "There is no category!"
            );
        }

        return courses.map(courseMapper::toCourseResponse);
    }
}
