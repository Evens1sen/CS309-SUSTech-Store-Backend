package com.project.store.service.impl;

import com.project.store.entity.Orders;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.mapper.OrdersMapper;
import com.project.store.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public boolean save(Product product, String useAddress, User user) {
        Orders orders = new Orders();
        orders.setBuyerId(user.getUid());
        orders.setOwnerId(product.getOwnerId());
        orders.setUserAddress(useAddress);
        orders.setProductId(product.getId());
        orders.setCost(product.getPrice());
        orders.setStatus(0);

        String seriaNumber = null;
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 32; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber = result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);

        int result = ordersMapper.insert(orders);
        return result > 0;
    }
}
