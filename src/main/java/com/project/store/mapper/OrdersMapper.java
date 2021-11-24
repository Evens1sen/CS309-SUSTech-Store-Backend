package com.project.store.mapper;

import com.project.store.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.store.vo.OrdersVO;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
}
