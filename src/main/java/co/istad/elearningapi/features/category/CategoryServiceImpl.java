package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {

        if(categoryRepository.existsByName(categoryCreateRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category is already existed!"
            );
        }

        Category category = categoryMapper.fromCategoryCreateRequest(categoryCreateRequest);
        if(category.getParentCategoryId().equals(0)){
            category.setParentCategoryId(null);
        }
        category.setIsDeleted(false);

        categoryRepository.save(category);

    }

    @Override
    public Page<CategoryResponse> findAllCategory(int page, int size) {

        if(page < 0 ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "page number must be greater than 0"
            );
        }

        if(size < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Size must be greater than 1!"
            );
        }


        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Category> categories = categoryRepository.findAll(pageRequest);

        if(categories.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "There is no category!"
            );
        }

        return categories.map(categoryMapper::toCategoryResponse);
    }

    @Override
    public List<CategoryResponse> findAllParentCategory() {

        List<Category> categories = categoryRepository.findAllByParentCategoryIdIsNull();

        return categoryMapper.toCategoryResponseList(categories);
    }

    @Override
    public CategoryResponse findCategoryByAlias(String alias) {

        if(!categoryRepository.existsByAlias(alias)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found!"
            );
        }

        Category category = categoryRepository.findByAlias(alias);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse editCategory(String alias, CategoryCreateRequest categoryCreateRequest) {

        if(!categoryRepository.existsByAlias(alias)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found!"
            );
        }

        Category category = categoryRepository.findByAlias(alias);

        return null;
    }
}
