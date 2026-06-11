package com.product_service.service;


import com.product_service.dto.ProductRequest;
import com.product_service.entity.Brand;
import com.product_service.entity.Category;
import com.product_service.entity.Product;
import com.product_service.entity.SubCategory;
import com.product_service.exception.ResourceNotFoundException;
import com.product_service.repository.BrandRepository;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.ProductRepository;
import com.product_service.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SubCategoryRepository subCategoryRepository;

    public Product createProduct(ProductRequest request){

        Category category =
                categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Category Not Found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        SubCategory subCategory= subCategoryRepository.findById(request.getSubCategoryId())
                .orElseThrow(() ->new ResourceNotFoundException("SubCategory Not Found"));

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);

        product.setCategory(category);
        product.setBrand(brand);
        product.setSubCategory(subCategory);

        return productRepository.save(product);
    }
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found "));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword){
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Product> getProductsByBrand(String brandName){
        return productRepository.findByBrandName(brandName);
    }

    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public List <Product> getProductsBySubCategory(String subCategoryName){
        return productRepository.findBySubCategoryName(subCategoryName);
    }


    public Product updateProduct(
            Long id,
            ProductRequest request){

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));

        productRepository.delete(product);
    }

    public Page<Product> getAllProducts(int page, int size, String sortBy ,String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable =
                PageRequest.of(page,size,sort);
        return productRepository.findAll(pageable);
    }

    public List <Product> getProductsByPriceRange
            (BigDecimal minPrice, BigDecimal maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

}
