package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.dto.category.CategoryDTO;
import com.shop.dto.category.UpdateCategoryDTO;
import com.shop.exceptions.DuplicateResourceException;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.CategoryMapper;
import com.shop.model.Category;
import com.shop.repository.CategoryRepository;
import com.shop.service.interfaces.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Service implementation for Category entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
        return categoryMapper.convertToDTO(category);
    }

    @Override
    public CategoryDTO getCategoryById(long id, String... with) {
        categoryMapper.verifyIncludes(with);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
        return categoryMapper.convertToDTO(category, with);
    }

    @Override
    public Page<CategoryDTO> getAllCategories(Pageable pageable, String search) {

        Page<Category> categoryPage;
        if (search != null && !search.isEmpty()) {
            categoryPage = categoryRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }

        return categoryPage.map(category -> categoryMapper.convertToDTO(category));
    }

    @Override
    public Page<CategoryDTO> getAllCategories(Pageable pageable, String search, String... with) {
        categoryMapper.verifyIncludes(with);
        Page<Category> categoryPage;

        if (search != null && !search.isEmpty()) {
            categoryPage = categoryRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }

        return categoryPage.map(category -> categoryMapper.convertToDTO(category, with));
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.findByName(categoryDTO.getName()).isPresent())
            throw new DuplicateResourceException("Category with this name already exists");
        Category category = categoryMapper.convertToEntity(categoryDTO);
        return categoryMapper.convertToDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(long categoryId, UpdateCategoryDTO categoryDTO) {
        Category categoryDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));

        if (categoryDTO.getName() != null && !categoryDTO.getName().isEmpty()) {
            categoryDB.setName(categoryDTO.getName());
        }
        if (categoryDTO.getDescription() != null && !categoryDTO.getName().isEmpty()) {
            categoryDB.setDescription(categoryDTO.getDescription());
        }

        return categoryMapper.convertToDTO(categoryRepository.save(categoryDB));
    }

    @Override
    public void deleteCategoryById(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

}
