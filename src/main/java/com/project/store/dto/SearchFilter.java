package com.project.store.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchFilter {
    @NotNull
    private String key;

    private Double minPrice;

    private Double maxPrice;

    private Double creditLevel;
}
