package com.project.store.service;

import com.project.store.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface OrdersService extends IService<Orders> {

    boolean save(@RequestBody Product product, @PathVariable String useAddress, User user);
}
