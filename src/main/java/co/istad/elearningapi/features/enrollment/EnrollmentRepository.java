package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long > {
    boolean existsByCode(String code);
    Enrollment findByCode(String code);

    @Modifying
    @Query("UPDATE Enrollment AS e SET e.isDeleted = TRUE WHERE e.code = ?1")
    void disableByCode(String code);

}
