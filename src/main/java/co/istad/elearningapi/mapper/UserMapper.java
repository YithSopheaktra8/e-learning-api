package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring",uses = {
        RoleMapper.class
})
public interface UserMapper {
 // @Mapping(source = "ListCountry", target = "country", qualifiedByName = "mapCountryResponse")

 @Mapping(source = "roles", target = "roleList")
 UserDetailsResponse toUserDetailResponse(User user);



}
