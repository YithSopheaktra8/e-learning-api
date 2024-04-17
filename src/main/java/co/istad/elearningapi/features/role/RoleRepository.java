package co.istad.elearningapi.features.role;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer > {

    Boolean existsByName(String name);

    Role findByName(String name);
}
