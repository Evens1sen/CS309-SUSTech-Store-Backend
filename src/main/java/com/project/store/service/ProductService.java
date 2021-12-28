package com.project.store.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.store.dto.SearchFilter;
import com.project.store.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.mapper.ProductMapper;
import com.project.store.util.RedisUtil;
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

    List<Product> findByCategoryId(Integer level, Integer categoryId);

    List<ProductVO> findProductVOByCategoryId(Integer level, Integer categoryId);

    ProductVO findProductVOById(Integer id);

    List<ProductVO> findAllProductVO();

    List<ProductVO> findProductVOPage(Integer pageNum, Integer pageSize);

    List<ProductVO> loadProductVOCache();

    List<ProductVO> searchAllProductVOPage(SearchFilter searchFilter, Integer pageNum, Integer pageSize);
  
}
