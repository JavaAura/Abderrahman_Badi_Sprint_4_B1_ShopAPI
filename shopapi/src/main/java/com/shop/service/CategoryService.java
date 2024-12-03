package com.shop.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shop.dto.category.CategoryDTO;
import com.shop.exceptions.InvalidDataException;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.CategoryMapper;
import com.shop.model.Category;
import com.shop.repository.CategoryRepository;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * Service interface for Category entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
public class CategoryService {

    // @Autowired
    // private CategoryRepository CategoryRepository;

    // @Autowired
    // private CategoryMapper CategoryMapper;

    // public CategoryDTO getCategoryById(long id) throws ResourceNotFoundException {
    //     Category Category = CategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    //     return CategoryMapper.convertToDTO(Category);
    // }

    // public CategoryDTO getCategoryById(long id, String... with) throws ResourceNotFoundException, InvalidDataException {
    //     CategoryMapper.verifyIncludes(with);
    //     Category Category = CategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    //     return CategoryMapper.convertToDTO(Category, with);
    // }

    // public List<CategoryDTO> getAllCategorys(String... with) throws InvalidDataException {
    //     CategoryMapper.verifyIncludes(with);
    //     List<Category> Categorys = CategoryRepository.findAll();
    //     return CategoryMapper.convertToDTOList(Categorys, with);
    // }

    // public List<CategoryDTO> getAllCategorys() {
    //     List<Category> Categorys = CategoryRepository.findAll();
    //     return CategoryMapper.convertToDTOList(Categorys);
    // }

    // public List<CategoryDTO> searchCategorys(CategoryCriteria criteria) throws ResourceNotFoundException {
    //     Specification<Category> spec = CategorySpecification.withFilters(criteria);
    //     List<Category> Categorys = CategoryRepository.findAll(spec);
    //     if (Categorys.isEmpty())
    //         throw new ResourceNotFoundException("Category not found");
    //     return CategoryMapper.convertToDTOList(Categorys);
    // }

    // public CategoryDTO addCategory(Category Category) {
    //     return CategoryMapper.convertToDTO(CategoryRepository.save(Category));
    // }

    // public CategoryDTO updateCategory(long CategoryId, Category Category) throws ResourceNotFoundException {
    //     Category CategoryDB = CategoryRepository.findById(CategoryId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

    //     if (StringUtils.isNotBlank(Category.getName())) {
    //         CategoryDB.setName(Category.getName());
    //     }
    //     if (StringUtils.isNotBlank(Category.getAddress())) {
    //         CategoryDB.setAddress(Category.getAddress());
    //     }
    //     if (Objects.nonNull(Category.getSurface())) {
    //         CategoryDB.setSurface(Category.getSurface());
    //     }

    //     return CategoryMapper.convertToDTO(CategoryRepository.save(CategoryDB));
    // }

    // public void deleteCategoryById(long CategoryId) throws ResourceNotFoundException {
    //     Category Category = CategoryRepository.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    //     CategoryRepository.delete(Category);
    // }

}
