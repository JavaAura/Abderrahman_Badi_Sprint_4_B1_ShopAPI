package com.shop.dto.category;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryDTO {

    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
    private String name;

    @Size(min = 3, max = 100, message = "Category description must be between 3 and 100 characters")
    private String description;
}
