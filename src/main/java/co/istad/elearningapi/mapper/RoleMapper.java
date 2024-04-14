package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AuthorityMapper.class)
public interface RoleMapper {
    @Mapping(source = "authorities", target = "authorityResponse", qualifiedByName = "mapAuthorityResponse")
    RoleResponse toRoleResponse(Role role);

    List<RoleResponse> toRoleResponseList(List<Role> roles);


}



