package com.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


/**
 * REST controller for managing Product entities by the admin.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

}
