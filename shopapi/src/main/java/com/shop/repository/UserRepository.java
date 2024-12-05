package com.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.User;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String userName);

}
