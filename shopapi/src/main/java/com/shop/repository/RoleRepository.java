package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.Role;

/**
 * Repository interface for Role entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
