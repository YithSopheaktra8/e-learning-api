package co.istad.elearningapi.features.student;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    Optional<Student> findByUser(User user);

}
