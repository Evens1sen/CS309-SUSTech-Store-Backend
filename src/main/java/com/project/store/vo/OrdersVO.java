package com.project.store.vo;

import com.project.store.enums.OrdersStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdersVO {
    private Integer id;

    private Integer buyerId;

    private String buyerNickName;

    private Integer sellerId;

    private String sellerNickName;

    private String productName;

    private Integer productId;

    private String image;

    private Float cost;

    private String serialnumber;

    private OrdersStatus status;

    private LocalDateTime createTime;

    private LocalDateTime expireTime;
}
