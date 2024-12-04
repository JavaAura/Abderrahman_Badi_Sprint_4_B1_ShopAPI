package com.shop.service.interfaces;

import java.util.List;

import com.shop.dto.product.ProductDTO;


/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface ProductService {

      ProductDTO getProductById(long id);

   ProductDTO getProductById(long id, String... with);

   List<ProductDTO> getAllProducts();

   List<ProductDTO> getAllProducts(String... with);

   ProductDTO addProduct(ProductDTO Product);

   public ProductDTO updateProduct(long ProductId, ProductDTO Product);

   public void deleteProductById(long ProductId);
}
