package com.project.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.entity.Cart;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.mapper.CartMapper;
import com.project.store.mapper.ProductMapper;
import com.project.store.mapper.UserMapper;
import com.project.store.service.CartService;
import com.project.store.vo.CartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Cart> findAllCartByUserId(Integer id) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return cartMapper.selectList(wrapper);
    }

    @Override
    public List<CartVO> findAllCartVOByUserId(Integer id) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("user_id", id);
        List<Cart> cartList = cartMapper.selectList(wrapper);
        List<CartVO> cartVOList = new ArrayList<>();
        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();
            Product product = productMapper.selectById(cart.getProductId());
            User owner = userMapper.selectById(product.getOwnerId());
            BeanUtils.copyProperties(product, cartVO);
            BeanUtils.copyProperties(owner, cartVO);
            cartVO.setId(cart.getId());
            cartVO.setProductId(cart.getProductId());
            cartVO.setProductType(product.getType());
            cartVOList.add(cartVO);
        }

        return cartVOList;
    }
}
