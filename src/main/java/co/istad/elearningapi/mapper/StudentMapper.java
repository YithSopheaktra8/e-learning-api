package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class
})
public interface StudentMapper {

    @Mapping(source = "user", target = "student", qualifiedByName = "toUserSnippetResponse")
    StudentResponse toStudentResponse(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void editUserStudentProfile(StudentUpdateRequest studentUpdateRequest, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void editStudentProfile(StudentUpdateRequest studentUpdateRequest, @MappingTarget Student student);

}
