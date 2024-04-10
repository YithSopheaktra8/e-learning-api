package co.istad.elearningapi.features.category;


import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> findAll(@RequestParam(defaultValue = "0",required = false) int page,
                                          @RequestParam(defaultValue = "10",required = false) int size){
        return categoryService.findAllCategory(page,size);

    }

    @GetMapping("/parents")
    public List<CategoryParentResponse> findAllParent(){
        return categoryService.findAllParentCategory();
    }

    @GetMapping("/{alias}")
    public CategoryResponse findByAlias(@PathVariable String alias){
        return categoryService.findCategoryByAlias(alias);
    }

    @PutMapping("/{alias}")
    public CategoryResponse editCategory(@PathVariable String alias,
                                         @Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.editCategory(alias,categoryRequest);
    }

    @PutMapping("/{alias}/disable")
    public BasedMessage disableCategory(@PathVariable String alias){
        return categoryService.disableCategory(alias);
    }


}
