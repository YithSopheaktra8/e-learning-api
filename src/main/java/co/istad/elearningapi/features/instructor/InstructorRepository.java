package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor , Integer> {

    Optional<Instructor> findInstructorByUserUserName(String username);

}
