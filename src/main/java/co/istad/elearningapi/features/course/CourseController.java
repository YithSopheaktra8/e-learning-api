package co.istad.elearningapi.features.course;

import co.istad.elearningapi.features.course.dto.CourseCreateRequest;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@Valid @RequestBody CourseCreateRequest courseCreateRequest){
        courseService.createNew(courseCreateRequest);
    }

    @GetMapping
    public Page<CourseResponse> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                        @RequestParam(defaultValue = "10",required = false) int size){
        return courseService.findAll(page,size);
    }

}
