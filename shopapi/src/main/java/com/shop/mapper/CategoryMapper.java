package com.shop.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shop.dto.category.CategoryDTO;
import com.shop.dto.product.ProductDTO;
import com.shop.exceptions.InvalidDataException;
import com.shop.model.Category;
import com.shop.model.Product;

@Component
public class CategoryMapper {
    private final List<String> VALID_INCLUDES = Arrays.asList("products");

    public void verifyIncludes(String... with)
            throws InvalidDataException {
        List<String> includesList = Arrays.asList(with);

        for (String include : includesList) {
            if (!include.isEmpty() && !VALID_INCLUDES.contains(include)) {
                throw new InvalidDataException("Invalid include: " + include);
            }
        }
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }

    public CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryDTO> convertToDTOList(List<Category> categorys) {
        return categorys.stream()
                .map(category -> convertToDTO(category))
                .collect(Collectors.toList());
    }

    public CategoryDTO convertToDTO(Category category, String... with) {
        List<String> includesList = Arrays.asList(with);

        List<ProductDTO> productDTOs = null;

        if (includesList.contains("products")) {
            List<Product> products = category.getProducts();
            productDTOs = products.stream().map(product -> ProductDTO.builder()
                    .designation(product.getDesignation())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build())
                    .collect(Collectors.toList());
        }

        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .products(productDTOs)
                .build();
    }

    public List<CategoryDTO> convertToDTOList(List<Category> categorys, String... with) {
        return categorys.stream()
                .map(category -> convertToDTO(category, with))
                .collect(Collectors.toList());
    }
}
