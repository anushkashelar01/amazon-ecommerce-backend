package com.shopease.service;

import com.shopease.entity.Category;
import com.shopease.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.shopease.entity.Product;
import com.shopease.repository.ProductRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public CategoryService(CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}