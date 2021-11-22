package com.project.store.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginParam {
    @NotNull(message = "uid不能为空")
    @ApiModelProperty(value = "uid", required = true)
    private Integer uid;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
