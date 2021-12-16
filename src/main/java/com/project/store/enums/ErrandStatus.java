package com.project.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum ErrandStatus {
    OPENED(0, "待接取"),
    RUNNING(1, "正在进行"),
    CLOSED(2, "已结束");

    @EnumValue
    int code;

    String meg;


    ErrandStatus(int code, String meg) {
        this.code = code;
        this.meg = meg;
    }
}
