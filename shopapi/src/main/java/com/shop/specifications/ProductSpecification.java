package com.shop.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.shop.model.Product;

public class ProductSpecification {

    public static Specification<Product> searchByDesignation(String term) {
        return (root, query, criteriaBuilder) -> {
            String pattern = "%" + term.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("designation")), pattern);
        };
    }

    public static Specification<Product> searchByCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }
}
