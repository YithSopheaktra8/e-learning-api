package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.features.user.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    @Mapping(source = "user.userName", target = "username")
    InstructorResponse instructorToInstructorResponse(Instructor instructor);


}
