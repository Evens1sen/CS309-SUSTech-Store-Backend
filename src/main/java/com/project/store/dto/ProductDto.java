package com.project.store.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.project.store.enums.ProductType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private Float price;

    private Integer ownerId;

    private Integer categoryleveloneId;

    private Integer categoryleveltwoId;

    private Integer categorylevelthreeId;

    private String[] images;

    private ProductType type;
}
