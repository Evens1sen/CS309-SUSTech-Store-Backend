package com.project.store.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRegisterParam {
    @NotNull(message = "用户id不能为空")
    @Min(value = 10000000, message = "账号长度必须是8位")
    @Max(value = 99999999, message = "账号长度必须是8位")
    @ApiModelProperty(value = "用户名", required = true)
    private Integer uid;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱")
    private String email;

}
