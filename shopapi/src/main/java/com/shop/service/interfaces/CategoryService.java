package com.shop.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.category.CategoryDTO;

/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface CategoryService {

   CategoryDTO getCategoryById(long id);

   CategoryDTO getCategoryById(long id, String... with);

   Page<CategoryDTO> getAllCategories(Pageable pageable, String search);

   Page<CategoryDTO> getAllCategories(Pageable pageable, String search, String... with);

   CategoryDTO addCategory(CategoryDTO Category);

   public CategoryDTO updateCategory(long CategoryId, CategoryDTO Category);

   public void deleteCategoryById(long CategoryId);
}
