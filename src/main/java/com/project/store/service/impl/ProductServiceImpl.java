package com.project.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.dto.SearchFilter;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.enums.ProductType;
import com.project.store.mapper.ProductMapper;
import com.project.store.mapper.UserMapper;
import com.project.store.service.ProductService;
import com.project.store.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Product> findByCategoryId(Integer level, Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        String levelStr;
        if (level == 1) {
            levelStr = "one";
        } else if (level == 2) {
            levelStr = "two";
        } else {
            levelStr = "three";
        }
        map.put("categorylevel" + levelStr + "_id", categoryId);
        return productMapper.selectByMap(map);
    }

    @Override
    public List<ProductVO> findProductVOByCategoryId(Integer level, Integer categoryId) {
        List<ProductVO> productVOList = new ArrayList<>();
        String levelStr;
        if (level == 1) {
            levelStr = "one";
        } else if (level == 2) {
            levelStr = "two";
        } else {
            levelStr = "three";
        }
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("categorylevel" + levelStr + "_id", categoryId);
        wrapper.orderByDesc("update_time");
        wrapper.eq("status", 0);
        List<Product> productList = productMapper.selectList(wrapper);
        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            User owner = userMapper.selectById(product.getOwnerId());
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(owner, productVO);
            productVOList.add(productVO);
        }

        return productVOList;
    }

    @Override
    public ProductVO findProductVOById(Integer id) {
        ProductVO productVO = new ProductVO();
        Product product = productMapper.selectById(id);
        User owner = userMapper.selectById(product.getOwnerId());
        BeanUtils.copyProperties(product, productVO);
        BeanUtils.copyProperties(owner, productVO);
        return productVO;
    }

    public List<ProductVO> findAllProductVO() {
        List<ProductVO> productVOList = new ArrayList<>();
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("status", 0);
        List<Product> productList = productMapper.selectList(wrapper);
        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            User owner = userMapper.selectById(product.getOwnerId());
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(owner, productVO);
            productVOList.add(productVO);
        }

        return productVOList;
    }

    @Override
    public List<ProductVO> findProductVOPage(Integer pageNum, Integer pageSize) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("status", 0);
        productMapper.selectPage(page, wrapper);

        List<ProductVO> productVOList = new ArrayList<>();
        List<Product> productList = page.getRecords();
        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            User owner = userMapper.selectById(product.getOwnerId());
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(owner, productVO);
            productVOList.add(productVO);
        }

        return productVOList;
    }

    @Override
    public List<ProductVO> searchAllProductVOPage(SearchFilter searchFilter, Integer pageNum, Integer pageSize) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchFilter.getKey());
        wrapper.orderByDesc("update_time");
        wrapper.eq("status", 0);

        if (searchFilter.getCategoryId() != 0) {
            wrapper.eq("categorylevelone_id", searchFilter.getCategoryId());
        }

        if (searchFilter.getMaxPrice() != 0) {
            wrapper.le("price", searchFilter.getMaxPrice());
        }
        if (searchFilter.getMinPrice() != 0) {
            wrapper.ge("price", searchFilter.getMinPrice());
        }

        if (searchFilter.getProductType() == ProductType.BUY){
            wrapper.eq("type", 1);
        }else {
            wrapper.eq("type", 0);
        }

        List<Product> productList = productMapper.selectList(wrapper);
        if (searchFilter.getCreditLevel() != 0) {
            Double credit = searchFilter.getCreditLevel();
            if (credit >= 4) {
                productList = productList.stream().filter(p ->
                        userMapper.selectById(p.getOwnerId()).getCredit() >= 100
                ).collect(Collectors.toList());
            } else if (credit >= 2.5) {
                productList = productList.stream().filter(p ->
                        userMapper.selectById(p.getOwnerId()).getCredit() >= 50
                ).collect(Collectors.toList());
            } else {
                productList = productList.stream().filter(p ->
                        userMapper.selectById(p.getOwnerId()).getCredit() >= 50
                ).collect(Collectors.toList());
            }
        }

        if (productList.size() == 0){
            return new ArrayList<>();
        }else if (pageNum * pageSize + 1 >= productList.size()) {
            productList = productList.subList((pageNum - 1) * pageSize, productList.size());
        } else {
            productList = productList.subList((pageNum - 1) * pageSize, pageNum * pageSize);
        }
        List<ProductVO> productVOList = new ArrayList<>();
        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            User owner = userMapper.selectById(product.getOwnerId());
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(owner, productVO);
            productVOList.add(productVO);
        }

        return productVOList;
    }

}
