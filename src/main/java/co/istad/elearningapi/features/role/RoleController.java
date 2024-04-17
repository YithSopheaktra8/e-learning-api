package co.istad.elearningapi.features.role;


import co.istad.elearningapi.features.role.dto.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    // Find All
    @GetMapping
    List<RoleResponse> findAll() {
        return roleService.findAll();
    }

    // Find by name
    @GetMapping("/{name}")
    RoleResponse findByByName(@PathVariable String name) {
        return roleService.findByName(name);
    }

}
