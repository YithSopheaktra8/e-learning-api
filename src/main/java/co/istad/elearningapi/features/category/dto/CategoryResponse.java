package co.istad.elearningapi.features.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record CategoryResponse(
        String name,
        String alias,
        String icon,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Integer parentCategoryId
) {
}
