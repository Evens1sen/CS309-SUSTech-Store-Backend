package com.project.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ProductStatus {
    FOR_SELL(0, "待售出"),
    SOLD(1, "已售出"),
    OFF_SHELF(2, "被下架");

    @EnumValue
    int code;

    String msg;

    ProductStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
