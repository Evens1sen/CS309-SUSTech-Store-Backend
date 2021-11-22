package com.project.store.service;

import com.project.store.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.mapper.ProductMapper;
import com.project.store.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface ProductService extends IService<Product> {

    List<Product> findByCategoryId(String level, Integer findByCategoryId);

    ProductVO findProductVOById(Integer id);
}
