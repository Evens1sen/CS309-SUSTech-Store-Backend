package com.project.store.service;

import com.project.store.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    public List<ProductCategoryVO> getAllProductCategoryVO();
}
