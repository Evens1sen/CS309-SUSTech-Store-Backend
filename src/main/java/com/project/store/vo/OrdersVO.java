package com.project.store.vo;

import com.project.store.enums.OrdersStatus;
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

    private OrdersStatus status;

    private LocalDateTime createTime;

    private LocalDateTime expireTime;
}
