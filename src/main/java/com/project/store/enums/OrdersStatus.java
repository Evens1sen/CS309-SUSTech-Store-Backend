package com.project.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrdersStatus {
    OPENED(0, "开启"),
    PAYED(1, "已支付"),
    CLOSED(2, "关闭");

    @EnumValue
    private int code;
    private String msg;

    OrdersStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
