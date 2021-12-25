package com.project.store.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.swing.plaf.basic.BasicIconFactory;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author zmt
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrdersComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer buy_id;

    private Integer sell_id;

    private Integer orders_id;

    private String comments;

    private Double star;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_at;
}
