package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shop.dto.product.ProductDTO;
import com.shop.dto.product.UpdateProductDTO;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.ProductMapper;
import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.interfaces.ProductService;
import com.shop.specifications.ProductSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Service implementation for Product entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.convertToDTO(product);
    }

    @Override
    public ProductDTO getProductById(long id, String... with) {
        productMapper.verifyIncludes(with);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.convertToDTO(product, with);
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable, String designation, Long categoryId) {
        Specification<Product> spec = Specification.where(null);

        if (designation != null && !designation.isEmpty()) {
            spec = spec.and(ProductSpecification.searchByDesignation(designation));
        }

        if (categoryId != null) {
            spec = spec.and(ProductSpecification.searchByCategory(categoryId));
        }

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        return productPage.map(product -> productMapper.convertToDTO(product));
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable, String designation, Long categoryId, String... with) {
        productMapper.verifyIncludes(with);
        Specification<Product> spec = Specification.where(null);

        if (designation != null && !designation.isEmpty()) {
            spec = spec.and(ProductSpecification.searchByDesignation(designation));
        }

        if (categoryId != null) {
            spec = spec.and(ProductSpecification.searchByCategory(categoryId));
        }

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        return productPage.map(product -> productMapper.convertToDTO(product, with));
    }

    @Override
    public Page<ProductDTO> getAllCategoryProducts(Pageable pageable, long categoryId) {
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);
        return productPage.map(product -> productMapper.convertToDTO(product));
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.convertToEntity(productDTO);
        return productMapper.convertToDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(long productId, UpdateProductDTO productDTO) {
        Product productDB = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        if (productDTO.getDesignation() != null && !productDTO.getDesignation().isEmpty()) {
            productDB.setDesignation(productDTO.getDesignation());
        }
        if (productDTO.getPrice() != null) {
            productDB.setPrice(productDTO.getPrice());
        }
        if (productDTO.getQuantity() != null) {
            productDB.setQuantity(productDTO.getQuantity());
        }
        if (productDTO.getCategory() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
            productDB.setCategory(category);
        }

        return productMapper.convertToDTO(productRepository.save(productDB));
    }

    @Override
    public void deleteProductById(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

}
