package com.project.store.vo;

import com.project.store.enums.ErrandStatus;
import com.project.store.enums.ErrandType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrandVO {

    private Integer id;

    private String name;

    private String description;

    private Float price;

    private String origin;

    private String destination;

    private Integer buyerId;

    private String buyerNickname;

    private Integer ownerId;

    private String ownerNickname;

    private String ownerIcon;

    private ErrandType type;

    private String image;

    private ErrandStatus status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
