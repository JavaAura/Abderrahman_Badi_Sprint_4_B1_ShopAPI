package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.Category;

/**
 * Repository interface for Category entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
