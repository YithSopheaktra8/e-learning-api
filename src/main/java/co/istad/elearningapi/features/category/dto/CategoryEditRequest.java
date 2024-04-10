package co.istad.elearningapi.features.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryEditRequest(@NotBlank(message = "Category name is required")
                                  String name,

                                  @NotBlank(message = "Category alias is required")
                                  String alias,

                                  @NotBlank(message = "Category icon is required")
                                  String icon,

                                  Integer parentCategoryId
) {
}
