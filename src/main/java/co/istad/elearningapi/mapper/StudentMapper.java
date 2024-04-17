package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class
})
public interface StudentMapper {

    @Mapping(source = "user", target = "student", qualifiedByName = "toUserDetailResponse")
    StudentResponse toStudentResponse(Student student);

}
