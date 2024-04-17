package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningapi.mapper.InstructorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    //private final UserRepository userRepository;

    @Override
    public InstructorResponse createInstructor(InstructorCreateRequest instructorCreateRequest) {
//        Instructor instructor = instructorMapper.instructorCreateRequestToInstructor(instructorCreateRequest, userRepository);
//        Instructor savedInstructor = instructorRepository.save(instructor);
//        return instructorMapper.instructorToInstructorResponse(savedInstructor);
        return null;
    }

    @Override
    public List<InstructorResponse> findAllInstructors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instructor> instructorPage = instructorRepository.findAll(pageable);
        return instructorPage.stream()
                .map(instructorMapper::instructorToInstructorResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorResponse updateInstructor(String username, InstructorCreateRequest instructorCreateRequest) {
//        Instructor instructor = instructorRepository.findInstructorByUserUserName(username)
//                .orElseThrow(() -> new RuntimeException("Instructor not found with username: " + username));
//        Instructor updatedInstructor = instructorMapper.instructorCreateRequestToInstructor(instructorCreateRequest, userRepository);
//        updatedInstructor.setId(instructor.getId());
//        updatedInstructor = instructorRepository.save(updatedInstructor);
        //return instructorMapper.instructorToInstructorResponse(updatedInstructor);
        return null;
    }

    @Override
    public InstructorResponse findInstructorByUsername(String username) {
        Instructor instructor = instructorRepository.findInstructorByUserUserName(username)
                .orElseThrow(() -> new RuntimeException("Instructor not found with username: " + username));
        return instructorMapper.instructorToInstructorResponse(instructor);
    }
}
