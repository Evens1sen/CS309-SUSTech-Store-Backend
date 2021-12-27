package com.project.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.entity.Cart;
import com.project.store.entity.Orders;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.enums.OrdersStatus;
import com.project.store.mapper.CartMapper;
import com.project.store.mapper.OrdersMapper;
import com.project.store.mapper.ProductMapper;
import com.project.store.mapper.UserMapper;
import com.project.store.service.OrdersService;
import com.project.store.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Integer save(Product product, String useAddress, User user) {
        Orders orders = new Orders();
        orders.setBuyerId(user.getUid());
        orders.setOwnerId(product.getOwnerId());
        orders.setUserAddress(useAddress);
        orders.setProductId(product.getId());
        orders.setCost(product.getPrice());
        orders.setStatus(OrdersStatus.OPENED);

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
        ordersMapper.insert(orders);

        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("serialnumber", seriaNumber);

        QueryWrapper<Cart> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("user_id", orders.getBuyerId());
        wrapper2.eq("product_id", orders.getProductId());
        Cart cart = cartMapper.selectOne(wrapper2);
        if (cart != null) {
            cartMapper.deleteById(cart.getId());
        }

        return ordersMapper.selectOne(wrapper).getId();
    }

    @Override
    public OrdersVO findOrdersVOByOrdersId(Integer id) {
        Orders orders = ordersMapper.selectById(id);
        OrdersVO ordersVO = new OrdersVO();
        Product product = productMapper.selectById(orders.getProductId());
        User buyer = userMapper.selectById(orders.getBuyerId());
        User owner = userMapper.selectById(product.getOwnerId());
        ordersVO.setId(orders.getId());
        ordersVO.setBuyerId(buyer.getUid());
        ordersVO.setBuyerNickName(buyer.getNickName());
        ordersVO.setSellerId(owner.getUid());
        ordersVO.setSellerNickName(owner.getNickName());
        ordersVO.setProductName(product.getName());
        ordersVO.setProductId(product.getId());
        ordersVO.setImage(product.getImage());
        ordersVO.setCost(orders.getCost());
        ordersVO.setSerialnumber(orders.getSerialnumber());
        ordersVO.setStatus(orders.getStatus());
        ordersVO.setCreateTime(orders.getCreateTime());
        ordersVO.setExpireTime(orders.getCreateTime().plusHours(1));


        return ordersVO;
    }

    @Override
    public List<OrdersVO> findAllOrdersVOByBuyerID(Integer id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("buyer_id", id);
        List<Orders> ordersList = ordersMapper.selectList(wrapper);
        List<OrdersVO> ordersVOList = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrdersVO ordersVO = new OrdersVO();
            Product product = productMapper.selectById(orders.getProductId());
            User buyer = userMapper.selectById(id);
            User owner = userMapper.selectById(product.getOwnerId());
            ordersVO.setId(orders.getId());
            ordersVO.setBuyerId(buyer.getUid());
            ordersVO.setBuyerNickName(buyer.getNickName());
            ordersVO.setSellerId(owner.getUid());
            ordersVO.setSellerNickName(owner.getNickName());
            ordersVO.setProductName(product.getName());
            ordersVO.setProductId(product.getId());
            ordersVO.setImage(product.getImage());
            ordersVO.setCost(orders.getCost());
            ordersVO.setSerialnumber(orders.getSerialnumber());
            ordersVO.setStatus(orders.getStatus());
            ordersVO.setCreateTime(orders.getCreateTime());
            ordersVO.setExpireTime(orders.getCreateTime().plusHours(1));
            ordersVOList.add(ordersVO);
        }

        return ordersVOList;
    }

    @Override
    public List<OrdersVO> findAllOrdersVOByOwnerID(Integer ownerId) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("owner_id", ownerId);
        List<Orders> ordersList = ordersMapper.selectList(wrapper);
        List<OrdersVO> ordersVOList = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrdersVO ordersVO = new OrdersVO();
            Product product = productMapper.selectById(orders.getProductId());
            User buyer = userMapper.selectById(orders.getBuyerId());
            User owner = userMapper.selectById(orders.getOwnerId());
            ordersVO.setId(orders.getId());
            ordersVO.setBuyerId(buyer.getUid());
            ordersVO.setBuyerNickName(buyer.getNickName());
            ordersVO.setSellerId(owner.getUid());
            ordersVO.setSellerNickName(owner.getNickName());
            ordersVO.setProductName(product.getName());
            ordersVO.setProductId(product.getId());
            ordersVO.setImage(product.getImage());
            ordersVO.setCost(orders.getCost());
            ordersVO.setSerialnumber(orders.getSerialnumber());
            ordersVO.setStatus(orders.getStatus());
            ordersVO.setCreateTime(orders.getCreateTime());
            ordersVO.setExpireTime(orders.getCreateTime().plusHours(1));
            ordersVOList.add(ordersVO);
        }

        return ordersVOList;
    }
}
