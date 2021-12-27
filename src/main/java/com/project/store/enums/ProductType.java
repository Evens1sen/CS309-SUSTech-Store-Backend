package com.project.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ProductType {
    SELL(0, "For sell"),
    BUY(1, "To buy");

    @EnumValue
    int code;

    String meg;

    ProductType(int code, String meg) {
        this.code = code;
        this.meg = meg;
    }
}
