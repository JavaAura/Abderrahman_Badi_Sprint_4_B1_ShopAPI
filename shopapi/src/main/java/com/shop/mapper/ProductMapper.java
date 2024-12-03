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
public class ProductMapper {
    private final List<String> VALID_INCLUDES = Arrays.asList("category");

    public void verifyIncludes(String... with)
            throws InvalidDataException {
        List<String> includesList = Arrays.asList(with);

        for (String include : includesList) {
            if (!include.isEmpty() && !VALID_INCLUDES.contains(include)) {
                throw new InvalidDataException("Invalid include: " + include);
            }
        }
    }

    public Product convertToEntity(ProductDTO productDTO) {
        return Product.builder().designation(productDTO.getDesignation())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .category(Category.builder().id(productDTO.getCategoryId()).build())
                .build();
    }

    public ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .build();
    }

    public List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(product -> convertToDTO(product))
                .collect(Collectors.toList());
    }

    public ProductDTO convertToDTO(Product product, String... with) {
        List<String> includesList = Arrays.asList(with);

        CategoryDTO categoryDTO = null;

        if (includesList.contains("category")) {
            Category category = product.getCategory();
            categoryDTO = CategoryDTO.builder()
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
        }

        return ProductDTO.builder()
                .designation(product.getDesignation())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(categoryDTO)
                .build();
    }

    public List<ProductDTO> convertToDTOList(List<Product> products, String... with) {
        return products.stream()
                .map(product -> convertToDTO(product, with))
                .collect(Collectors.toList());
    }
}
