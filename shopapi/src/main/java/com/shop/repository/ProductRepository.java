package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.Product;

/**
 * Repository interface for Product entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



}
