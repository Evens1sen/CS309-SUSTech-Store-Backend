package com.project.store.service;

import com.project.store.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.entity.Product;
import com.project.store.vo.CartVO;
import com.project.store.vo.ProductVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface CartService extends IService<Cart> {

    List<Cart> findAllCartByUserId(Integer id);

    List<CartVO> findAllCartVOByUserId(Integer id);
}
