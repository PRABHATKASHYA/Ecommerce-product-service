package com.product_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    private String categoryName;
    private String subCategoryName;
    private String brandName;
}
