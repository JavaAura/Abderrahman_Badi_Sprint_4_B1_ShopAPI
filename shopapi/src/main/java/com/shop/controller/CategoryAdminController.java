package com.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * REST controller for managing Category entities by the admin.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/admin/categories")
public class CategoryAdminController {

    @GetMapping()
    public String getMethodName(@RequestParam String param) {
        return new String("admin categories");
    }
    

}
