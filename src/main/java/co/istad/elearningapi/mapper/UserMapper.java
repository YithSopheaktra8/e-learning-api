package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import co.istad.elearningapi.features.user.dto.UserSnippetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        RoleMapper.class,
        CountryMapper.class,
        CityMapper.class
})
public interface UserMapper {

    // @Mapping(source = "ListCountry", target = "country", qualifiedByName = "mapCountryResponse")

    @Mapping(source = "roles", target = "roleList")
    UserDetailsResponse toUserDetailResponse(User user);

    @Named("toUserSnippetResponse")
    UserSnippetResponse toUserSnippetResponse(User user);

    User fromStudentCreateRequest(StudentCreateRequest studentCreateRequest);


}
