package com.product_service.controller;

import com.product_service.entity.Brand;
import com.product_service.entity.Product;
import com.product_service.repository.BrandRepository;
import com.product_service.repository.ProductRepository;
import com.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandRepository brandRepository;
    private final ProductService productService;

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand){
        return brandRepository.save(brand);
    }

    @GetMapping
    public List<Brand> getAllBrands(){
        return brandRepository.findAll();

    }

    @GetMapping("/brand/{brandName}")
    public List<Product> getProductsByBrand(@PathVariable String brandName){
        return productService.getProductsByBrand(brandName);
    }
}
