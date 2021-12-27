package com.project.store.dto;

import com.project.store.enums.ProductType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchFilter {
    @NotNull
    private String key;

    private Double minPrice;

    private Double maxPrice;

    private Double creditLevel;

    private Integer categoryId;

    private ProductType productType = ProductType.SELL;
}
