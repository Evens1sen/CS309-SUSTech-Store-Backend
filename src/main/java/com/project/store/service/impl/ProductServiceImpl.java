package com.project.store.service.impl;

import com.project.store.entity.Product;
import com.project.store.mapper.ProductMapper;
import com.project.store.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findByCategoryId(String level, Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("categorylevel" + level + "_id", categoryId);
        return productMapper.selectByMap(map);
    }
}
