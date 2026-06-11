package com.product_service.controller;

import com.product_service.dto.ProductRequest;
import com.product_service.entity.Product;
import com.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public Product getProductById( @PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product>getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword){
        return productService.searchProducts(keyword);

    }
    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(
            @PathVariable String categoryName){
        return productService.getProductsByCategory(categoryName);
    }


    @GetMapping("/subcategory/{subCategoryName}")
    public List <Product> getProductsBySubCategory(
            @PathVariable String subCategoryName){
        return productService.getProductsBySubCategory(subCategoryName);
    }

    @GetMapping("/paged")
    public Page<Product> getAllProducts(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "price") String sortBy,
            @RequestParam (defaultValue = "asc") String direction){

        return productService.getAllProducts(page, size,sortBy, direction);
    }


    @GetMapping("/filter")
    public List<Product> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice){
        return productService.getProductsByPriceRange(minPrice, maxPrice);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request){

        return productService.updateProduct(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id){

        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }
}
