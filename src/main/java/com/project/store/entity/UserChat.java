package com.project.store.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-11-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class UserChat implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private Integer buyId;

    private Integer sellId;

    private String lineText;

      /**
     * 时间
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createAt;

}
