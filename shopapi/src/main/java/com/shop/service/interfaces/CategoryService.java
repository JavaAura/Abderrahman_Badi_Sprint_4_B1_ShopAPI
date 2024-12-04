package com.shop.service.interfaces;

import java.util.List;

import com.shop.dto.category.CategoryDTO;

/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface CategoryService {

   CategoryDTO getCategoryById(long id);

   CategoryDTO getCategoryById(long id, String... with);

   List<CategoryDTO> getAllCategorys();

   List<CategoryDTO> getAllCategorys(String... with);

   CategoryDTO addCategory(CategoryDTO Category);

   public CategoryDTO updateCategory(long CategoryId, CategoryDTO Category);

   public void deleteCategoryById(long CategoryId);
}
