package com.project.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ErrandType {
    FETCH(0, "Help me fetch"),
    SEND(1, "Help me send"),
    DO(2, "Help me do");

    @EnumValue
    int code;

    String meg;


    ErrandType(int code, String meg) {
        this.code = code;
        this.meg = meg;
    }
}
