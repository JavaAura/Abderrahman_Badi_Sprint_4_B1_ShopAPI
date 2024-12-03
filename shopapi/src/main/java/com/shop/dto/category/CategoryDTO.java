package com.shop.dto.category;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shop.dto.product.ProductDTO;

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
public class CategoryDTO {

    @NotNull(message = "Category name cannot be null")
    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Category description cannot be null")
    @Size(min = 3, max = 100, message = "Category description must be between 3 and 100 characters")
    private String description;

    private List<ProductDTO> products;
}
