package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAllByIsDeletedFalse(PageRequest pageRequest);

    Optional<Course> findByAlias(String alias);

    @Modifying
    @Query("UPDATE Course as c SET c.isDeleted = true WHERE c.alias = ?1")
    void disableCourseByAlias(String alias);

    boolean existsByAlias(String alias);

    Optional<Course> findByTitle(String title);
}
