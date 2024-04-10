package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromCategoryCreateRequest(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryParentResponse> toCategoryParentResponse(List<Category> categories);

}
