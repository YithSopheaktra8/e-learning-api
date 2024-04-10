package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAllByIsDeletedFalse(PageRequest pageRequest);

}
