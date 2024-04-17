package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Role;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.role.dto.RoleResponse;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserSnippetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


  UserDetailsResponse toUserDetailResponse (User user);

  @Named("toUserSnippetResponse")
  UserSnippetResponse toUserSnippetResponse(User user);




}
