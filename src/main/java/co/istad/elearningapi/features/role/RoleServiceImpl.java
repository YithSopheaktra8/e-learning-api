package co.istad.elearningapi.features.role;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import co.istad.elearningapi.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponse> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.mapRoleToResponseList(roles);
    }

    @Override
    public RoleResponse findByName(String name) {
        if (!roleRepository.existsByName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Role does not exist!"
            );
        }
        Role role = roleRepository.findByName(name);
        RoleResponse roleResponse = roleMapper.toRoleResponse(role);
        return roleResponse;
    }

}
