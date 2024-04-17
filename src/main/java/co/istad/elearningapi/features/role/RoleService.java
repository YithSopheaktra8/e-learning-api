package co.istad.elearningapi.features.role;

import co.istad.elearningapi.features.role.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> findAll();

    RoleResponse findByName(String name);
}
