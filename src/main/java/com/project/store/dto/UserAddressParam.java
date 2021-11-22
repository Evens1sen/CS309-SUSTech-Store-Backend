package com.project.store.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAddressParam {

    @NotNull(message = "地址不能为空")
    private String address;

    private String remark;

    private Integer isDefault;
}
