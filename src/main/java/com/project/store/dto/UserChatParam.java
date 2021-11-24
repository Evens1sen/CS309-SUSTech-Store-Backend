package com.project.store.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserChatParam {
    @NotNull(message = "消息不能为空")

    private Integer sellId;

    private String lineText;
}
