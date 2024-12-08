package com.shop.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.shop.dto.product.ProductDTO;
import com.shop.dto.product.UpdateProductDTO;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.ProductMapper;
import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductServiceImpl;

public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = Product.builder()
                .id(1L)
                .designation("Test Product")
                .price(100.0)
                .quantity(10)
                .category(Category.builder().id(1L).name("Test Category").build())
                .build();

        productDTO = ProductDTO.builder()
                .id(1L)
                .designation("Test Product")
                .price(100.0)
                .quantity(10)
                .build();
    }

    @Test
    void testGetProductById_WhenFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.convertToDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getDesignation());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_WhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllCategoryProducts() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));
        when(productRepository.findByCategoryId(1L, pageable)).thenReturn(productPage);
        when(productMapper.convertToDTO(product)).thenReturn(productDTO);

        Page<ProductDTO> result = productService.getAllCategoryProducts(pageable, 1L);

        assertEquals(1, result.getContent().size());
        assertEquals("Test Product", result.getContent().get(0).getDesignation());
        verify(productRepository, times(1)).findByCategoryId(1L, pageable);
    }

    @Test
    void testAddProduct() {
        when(productMapper.convertToEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.convertToDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.addProduct(productDTO);

        assertNotNull(result);
        assertEquals("Test Product", result.getDesignation());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_WhenFound() {
        UpdateProductDTO updateProductDTO = UpdateProductDTO.builder()
                .designation("Updated Product")
                .price(200.0)
                .quantity(20)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.convertToDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.updateProduct(1L, updateProductDTO);

        assertNotNull(result);
        assertEquals("Test Product", result.getDesignation());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_WhenNotFound() {
        UpdateProductDTO updateProductDTO = UpdateProductDTO.builder()
                .designation("Updated Product")
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1L, updateProductDTO));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteProductById_WhenFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProductById(1L);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testDeleteProductById_WhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

}
