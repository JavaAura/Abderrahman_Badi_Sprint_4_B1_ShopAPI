package com.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.category.CategoryDTO;
import com.shop.dto.product.ProductDTO;
import com.shop.service.interfaces.CategoryService;
import com.shop.service.interfaces.ProductService;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing Category entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/user/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping()
    public Page<CategoryDTO> getAllCategories(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by("id").ascending());
        return categoryService.getAllCategories(pageable, name);
    }

    @GetMapping("/{id}/products")
    public Page<ProductDTO> getAllCategoryProducts(
            @PathVariable("id") long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by("id").ascending());
        return productService.getAllCategoryProducts(pageable, categoryId);
    }

}
