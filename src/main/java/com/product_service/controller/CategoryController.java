package com.product_service.controller;

import com.product_service.entity.Category;
import com.product_service.entity.Product;
import com.product_service.repository.CategoryRepository;
import com.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/category/{categoryName}")
    public List<Product>getProductsByCategory(
            @PathVariable String categoryName){
        return productService.getProductsByCategory(categoryName);    }

}
