package com.shop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.product.ProductDTO;
import com.shop.dto.product.UpdateProductDTO;
import com.shop.service.interfaces.ProductService;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing Product entities by the admin.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductService productService;

    @PostMapping
    public ProductDTO saveProduct(
            @RequestBody @Valid ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProductDTO(
            @RequestBody @Valid UpdateProductDTO updateProductDTO,
            @PathVariable("id") Long productId) {
        return productService.updateProduct(productId, updateProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateProductDTO(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.noContent().build();
    }
}
