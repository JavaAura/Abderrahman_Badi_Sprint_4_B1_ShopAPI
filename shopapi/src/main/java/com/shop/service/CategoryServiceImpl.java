package com.shop.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shop.dto.category.CategoryDTO;
import com.shop.exceptions.InvalidDataException;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.CategoryMapper;
import com.shop.model.Category;
import com.shop.repository.CategoryRepository;
import com.shop.service.interfaces.CategoryService;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Service implementation for Category entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository CategoryRepository;

    @Autowired
    private CategoryMapper CategoryMapper;

    @Override
    public CategoryDTO getCategoryById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    @Override
    public CategoryDTO getCategoryById(long id, String... with) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    @Override
    public Page<CategoryDTO> getAllCategorys() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategorys'");
    }

    @Override
    public Page<CategoryDTO> getAllCategorys(String... with) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategorys'");
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO Category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCategory'");
    }

    @Override
    public CategoryDTO updateCategory(long CategoryId, CategoryDTO Category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }

    @Override
    public void deleteCategoryById(long CategoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategoryById'");
    }

    // public CategoryDTO getCategoryById(long id) throws ResourceNotFoundException
    // {
    // Category Category = CategoryRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    // return CategoryMapper.convertToDTO(Category);
    // }

    // public CategoryDTO getCategoryById(long id, String... with) throws
    // ResourceNotFoundException, InvalidDataException {
    // CategoryMapper.verifyIncludes(with);
    // Category Category = CategoryRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    // return CategoryMapper.convertToDTO(Category, with);
    // }

    // public List<CategoryDTO> getAllCategorys(String... with) throws
    // InvalidDataException {
    // CategoryMapper.verifyIncludes(with);
    // List<Category> Categorys = CategoryRepository.findAll();
    // return CategoryMapper.convertToDTOList(Categorys, with);
    // }

    // public List<CategoryDTO> getAllCategorys() {
    // List<Category> Categorys = CategoryRepository.findAll();
    // return CategoryMapper.convertToDTOList(Categorys);
    // }

    // public CategoryDTO addCategory(Category Category) {
    // return CategoryMapper.convertToDTO(CategoryRepository.save(Category));
    // }

    // public CategoryDTO updateCategory(long CategoryId, Category Category) throws
    // ResourceNotFoundException {
    // Category CategoryDB = CategoryRepository.findById(CategoryId)
    // .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

    // if (StringUtils.isNotBlank(Category.getName())) {
    // CategoryDB.setName(Category.getName());
    // }
    // if (StringUtils.isNotBlank(Category.getAddress())) {
    // CategoryDB.setAddress(Category.getAddress());
    // }
    // if (Objects.nonNull(Category.getSurface())) {
    // CategoryDB.setSurface(Category.getSurface());
    // }

    // return CategoryMapper.convertToDTO(CategoryRepository.save(CategoryDB));
    // }

    // public void deleteCategoryById(long CategoryId) throws
    // ResourceNotFoundException {
    // Category Category = CategoryRepository.findById(CategoryId)
    // .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    // CategoryRepository.delete(Category);
    // }

}
