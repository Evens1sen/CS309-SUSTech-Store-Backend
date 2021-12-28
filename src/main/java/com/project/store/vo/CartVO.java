package com.project.store.vo;

import com.project.store.enums.ProductType;
import lombok.Data;

@Data
public class CartVO {
    private Integer id;

    private Integer productId;

    private ProductType productType;

    private String name;

    private String description;

    private Float price;

    private Integer ownerId;

    private String image;

    private String nickName;

    private String icon;
}
