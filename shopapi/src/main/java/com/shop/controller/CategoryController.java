package com.shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.category.CategoryDTO;
import com.shop.service.interfaces.CategoryService;

import lombok.AllArgsConstructor;

/**
 * REST controller for managing Category entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/user/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping()
    public Page<CategoryDTO> getMethodName(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by("id").ascending());
        return categoryService.getAllCategories(pageable, name);
    }

}
