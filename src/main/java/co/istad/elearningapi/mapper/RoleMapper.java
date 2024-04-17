package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
   // @Mapping(source = "authorities", target = "authorityResponse", qualifiedByName = "mapAuthorityResponse")
    RoleResponse toRoleResponse(Role role);

    default List<RoleResponse> mapRoleToResponseList(List<Role> roles) {
        return roles.stream()
                .map(this::toRoleResponse).toList();
    }
}





