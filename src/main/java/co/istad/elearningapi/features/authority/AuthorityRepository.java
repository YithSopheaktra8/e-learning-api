package co.istad.elearningapi.features.authority;

import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer > {
}
