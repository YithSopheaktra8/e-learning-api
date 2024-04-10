package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BasedMessage;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryRequest categoryRequest);

    Page<CategoryResponse> findAllCategory(int page, int size);

    List<CategoryParentResponse> findAllParentCategory();

    CategoryResponse findCategoryByAlias(String alias);

    CategoryResponse editCategory(String alias, CategoryRequest categoryRequest);

    BasedMessage disableCategory(String alias);

}
