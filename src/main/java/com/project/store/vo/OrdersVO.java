package com.project.store.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdersVO {
    private Integer id;

    private String buyerNickName;

    private String sellerNickName;

    private String productName;

    private String image;

    private Float cost;

    private String serialnumber;

    private Integer status;

    private LocalDateTime createTime;
}
