package com.project.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.entity.OrdersComment;

/**
 * @author ZMT
 */

public interface OrdersCommentService extends IService<OrdersComment> {
    int queryCredit(Integer id);
    double getDiscount(Integer id);
    int updateCredit(Integer id,Double star);
    String commentOrders(Integer id , String comment,Double star);
}