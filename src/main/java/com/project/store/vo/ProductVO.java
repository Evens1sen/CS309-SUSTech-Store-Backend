package com.project.store.vo;

import lombok.Data;

@Data
public class ProductVO {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;

    private Float price;

    private Integer ownerId;

    private String image;

    private String nickName;

    private String icon;

}
