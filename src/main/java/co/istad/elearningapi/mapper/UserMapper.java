package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
 // @Mapping(source = "ListCountry", target = "country", qualifiedByName = "mapCountryResponse")
  UserDetailsResponse toUserDetailResponse (User user);


}
