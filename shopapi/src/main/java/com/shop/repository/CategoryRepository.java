package com.shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.Category;

/**
 * Repository interface for Category entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByName(String name);

    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
