package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryCreateRequest categoryCreateRequest);

    Page<CategoryResponse> findAllCategory(int page, int size);

    List<CategoryResponse> findAllParentCategory();

    CategoryResponse findCategoryByAlias(String alias);

    CategoryResponse editCategory(String alias,CategoryCreateRequest categoryCreateRequest);

}
