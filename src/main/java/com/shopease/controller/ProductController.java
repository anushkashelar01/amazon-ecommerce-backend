package com.shopease.controller;

import com.shopease.entity.Product;
import com.shopease.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ ADD PRODUCT + LINK CATEGORY (ADMIN)
    @PostMapping("/admin/products/{categoryId}")
    public Product addProduct(
            @RequestBody Product product,
            @PathVariable Long categoryId
    ) {
        return productService.addProduct(product, categoryId);
    }

    // ✅ VIEW ALL PRODUCTS (USER / ADMIN)
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ DELETE PRODUCT (ADMIN)
    @DeleteMapping("/admin/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}