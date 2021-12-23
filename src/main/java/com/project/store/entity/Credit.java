package com.project.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zmt
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Credit {
    @TableId(value = "number", type = IdType.AUTO)
    private Integer number;
}
