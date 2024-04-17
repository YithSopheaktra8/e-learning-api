package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.base.BaseResponse;
import co.istad.elearningapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewInstructor(@Valid @RequestBody InstructorCreateRequest instructorCreateRequest) {
        instructorService.createInstructor(instructorCreateRequest);
    }


    @GetMapping
    public Page<InstructorResponse> findAllInstructors(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size){
        return instructorService.findAllInstructors(page, size);
    }

    @GetMapping("/{username}")
    InstructorResponse findInstructorByUsername(@PathVariable String username){
        return instructorService.findInstructorByUsername(username);
    }

    @PutMapping("/{username}")
    InstructorResponse updateInstructor(@PathVariable String username,
                                        @RequestBody InstructorCreateRequest instructorCreateRequest){
        return instructorService.updateInstructor(username, instructorCreateRequest);
    }
}
