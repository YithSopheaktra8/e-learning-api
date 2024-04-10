package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.category.CategoryRepository;
import co.istad.elearningapi.features.course.dto.*;
import co.istad.elearningapi.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public CourseDetailsResponse findByAlias(String alias) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course has not been found"
                ));

        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public BasedMessage editCourseByAlias(String alias , CourseUpdateRequest courseUpdateRequest) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course has not been found"
                ));

        Category category = categoryRepository.findByAlias(courseUpdateRequest.categoryAlias())
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found!"
                ));

        course.setTitle(courseUpdateRequest.title());
        course.setAlias(courseUpdateRequest.alias());
        course.setCategory(category);
        course.setIsDeleted(false);
        course.setDescription(courseUpdateRequest.description());
        course.setIsFree(courseUpdateRequest.isFree());
        course.setThumbnail(courseUpdateRequest.thumbnail());

        courseRepository.save(course);

        return new BasedMessage("Course update Successfully");

    }

    @Override
    public BasedMessage editCourseThumbnailByAlias(String alias, CourseThumbnailRequest courseThumbnailRequest) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course has not been found"
                ));

        course.setThumbnail(courseThumbnailRequest.thumbnail());

        courseRepository.save(course);

        return new BasedMessage("Course thumbnail has been updated successfully");
    }

    @Override
    public BasedMessage editCourseCategoryByAlias(String alias, CourseCategoryRequest categoryRequest) {

        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course has not been found"
                ));

        Category category = categoryRepository.findByAlias(categoryRequest.categoryAlias())
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found!"
                ));

        if(category.getAlias().equals(categoryRequest.categoryAlias())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category is the same can't not update"
            );
        }

        course.setCategory(category);

        courseRepository.save(course);

        return new BasedMessage("Course category has been updated successfully!");
    }

    @Transactional
    @Override
    public BasedMessage disableCourseByAlias(String alias) {

        if(!courseRepository.existsByAlias(alias)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course has not been found"
            );
        }

        courseRepository.disableCourseByAlias(alias);

        return new BasedMessage("Course has been disabled");
    }
}
