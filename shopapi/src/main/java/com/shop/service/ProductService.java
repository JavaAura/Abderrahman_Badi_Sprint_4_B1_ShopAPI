package com.shop.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.product.ProductDTO;
import com.shop.exceptions.InvalidDataException;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.ProductMapper;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Service interface for Product entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
public class ProductService {

    // @Autowired
    // private ProductRepository ProductRepository;

    // @Autowired
    // private ProductMapper ProductMapper;

    // public ProductDTO getProductById(long id) throws ResourceNotFoundException {
    //     Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    //     return ProductMapper.convertToDTO(Product);
    // }

    // public ProductDTO getProductById(long id, String... with) throws ResourceNotFoundException, InvalidDataException {
    //     ProductMapper.verifyIncludes(with);
    //     Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    //     return ProductMapper.convertToDTO(Product, with);
    // }

    // public List<ProductDTO> getAllProducts(String... with) throws InvalidDataException {
    //     ProductMapper.verifyIncludes(with);
    //     List<Product> Products = ProductRepository.findAll();
    //     return ProductMapper.convertToDTOList(Products, with);
    // }

    // public List<ProductDTO> getAllProducts() {
    //     List<Product> Products = ProductRepository.findAll();
    //     return ProductMapper.convertToDTOList(Products);
    // }

    // public ProductDTO addProduct(Product Product) throws InvalidSurfaceException {
    //     if (!isProductSurfaceValid(Product))
    //         throw new InvalidSurfaceException("Surface provided surpasses farm capacity");
    //     return ProductMapper.convertToDTO(ProductRepository.save(Product));
    // }

    // public ProductDTO updateProduct(long ProductId, Product Product) throws ResourceNotFoundException, InvalidSurfaceException {
    //     Product ProductDB = ProductRepository.findById(ProductId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    //     if (Product.getFarm() != null) {
    //         ProductDB.setFarm(Product.getFarm());
    //     }

    //     if (Objects.nonNull(Product.getSurface())) {
    //         ProductDB.setSurface(Product.getSurface());
    //     }

    //     if (Product.getSurface() != null && !isProductSurfaceValid(ProductDB))
    //         throw new InvalidSurfaceException("Surface provided surpasses farm capacity");

    //     return ProductMapper.convertToDTO(ProductRepository.save(ProductDB));
    // }

    // public void deleteProductById(long ProductId) throws ResourceNotFoundException {
    //     Product Product = ProductRepository.findById(ProductId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    //     ProductRepository.delete(Product);
    // }

    // public boolean isProductSurfaceValid(Product Product) {
    //     return ProductRepository.checkProductSurface(Product.getFarm().getId(), Product.getSurface().doubleValue(),
    //             Product.getId() == null ? 0 : ProductRepository.getProductSurface(Product.getId()).doubleValue());
    // }

}
