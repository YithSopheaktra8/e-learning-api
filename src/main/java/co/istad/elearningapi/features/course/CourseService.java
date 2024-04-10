package co.istad.elearningapi.features.course;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.course.dto.*;
import org.springframework.data.domain.Page;


public interface CourseService {

    void createNew(CourseCreateRequest courseCreateRequest);

    Page<CourseResponse> findAll(int page, int size);

    CourseDetailsResponse findByAlias(String alias);

    BasedMessage editCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest);

    BasedMessage editCourseThumbnailByAlias(String alias, CourseThumbnailRequest courseThumbnailRequest);

    BasedMessage editCourseCategoryByAlias(String alias, CourseCategoryRequest categoryRequest);

    BasedMessage disableCourseByAlias(String alias);
}
