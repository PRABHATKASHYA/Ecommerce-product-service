package com.product_service.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    private Long categoryId;
    private Long subCategoryId;
    private Long brandId;
}
