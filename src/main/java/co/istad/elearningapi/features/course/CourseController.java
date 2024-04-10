package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.course.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@Valid @RequestBody CourseCreateRequest courseCreateRequest){
        courseService.createNew(courseCreateRequest);
    }

    @GetMapping
    public Page<CourseResponse> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                        @RequestParam(defaultValue = "10",required = false) int size){
        return courseService.findAll(page,size);
    }

    @GetMapping("/{alias}")
    public CourseDetailsResponse findByAlias(@PathVariable String alias){
        return courseService.findByAlias(alias);
    }

    @PutMapping("/{alias}")
    public BasedMessage editCourse(@PathVariable String alias,
                                   @Valid @RequestBody CourseUpdateRequest courseUpdateRequest){
        return courseService.editCourseByAlias(alias,courseUpdateRequest);
    }

    @PutMapping("/{alias}/thumbnail")
    public BasedMessage editThumbnail(@PathVariable String alias,
                                      @Valid @RequestBody CourseThumbnailRequest courseThumbnailRequest){
        return courseService.editCourseThumbnailByAlias(alias,courseThumbnailRequest);
    }

    @PutMapping("/{alias}/categories")
    public BasedMessage editCourseCategory(@PathVariable String alias,
                                           @Valid @RequestBody CourseCategoryRequest categoryRequest){
        return courseService.editCourseCategoryByAlias(alias,categoryRequest);
    }

    @PutMapping("/{alias}/disable")
    public BasedMessage disableCourse(@PathVariable String alias){
        return courseService.disableCourseByAlias(alias);
    }

}
