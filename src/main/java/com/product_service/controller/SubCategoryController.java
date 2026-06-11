package com.product_service.controller;

import com.product_service.dto.SubCategoryRequest;
import com.product_service.entity.Brand;
import com.product_service.entity.Category;
import com.product_service.entity.SubCategory;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping
    public SubCategory createSubCategory(
            @RequestBody SubCategoryRequest request){
        Category category =
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() ->
                                new RuntimeException("Category Not Found"));

        SubCategory subCategory = new SubCategory();

        subCategory.setName(request.getName());
        subCategory.setCategory(category);

        return subCategoryRepository.save(subCategory);
    }

    @GetMapping
    public List<SubCategory> getAllSubCategory(){
        return subCategoryRepository.findAll();
    }
}
