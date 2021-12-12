package com.project.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrdersStatus {
    OPENED(0, "买家已下单"),
    PAYED(1, "买家已支付"),
    SHIPPED(2, "卖家已发货"),
    CONFIRMED(3, "买家已确认"),
    CLOSED(4, "已关闭");

    @EnumValue
    private final int code;

    private final String msg;

    OrdersStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
