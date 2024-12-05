package com.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.category.CategoryDTO;
import com.shop.dto.category.UpdateCategoryDTO;
import com.shop.service.interfaces.CategoryService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing Category entities by the admin.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDTO saveCategory(
            @RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategoryDTO(
            @RequestBody @Valid UpdateCategoryDTO updateCategoryDTO,
            @PathVariable("id") Long categoryId) {
        return categoryService.updateCategory(categoryId, updateCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateCategoryDTO(@PathVariable("id") long categoryId) {
        categoryService.deleteCategoryById(categoryId);

        return ResponseEntity.noContent().build();
    }

}
