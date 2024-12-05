package com.shop.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.product.ProductDTO;
import com.shop.dto.product.UpdateProductDTO;

/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface ProductService {

   ProductDTO getProductById(long id);

   ProductDTO getProductById(long id, String... with);

   Page<ProductDTO> getAllProducts(Pageable pageable, String search);

   Page<ProductDTO> getAllProducts(Pageable pageable, String search, String... with);

   Page<ProductDTO> getAllCategoryProducts(Pageable pageable, long id);

   ProductDTO addProduct(ProductDTO Product);

   public ProductDTO updateProduct(long ProductId, UpdateProductDTO Product);

   public void deleteProductById(long ProductId);
}
