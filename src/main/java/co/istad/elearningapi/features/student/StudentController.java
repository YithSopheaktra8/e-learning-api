package co.istad.elearningapi.features.student;

import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.createStudent(studentCreateRequest);
    }

    @GetMapping
    public Page<StudentResponse> findAll(@RequestParam(defaultValue = "0",required = false) int page,
                                         @RequestParam(defaultValue = "10",required = false) int size){
        return studentService.findAllStudent(page,size);
    }

    @GetMapping("/{username}")
    public StudentResponse findByUsername(@PathVariable String username){
        return studentService.findByUsername(username);
    }

    @PutMapping("/{username}")
    public void editStudentProfile(@PathVariable String username,
                                   @RequestBody StudentUpdateRequest studentUpdateRequest){
        studentService.editStudentProfile(username,studentUpdateRequest);
    }

}
