package com.project.store.service;

import com.project.store.entity.Errand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.vo.ErrandVO;
import com.project.store.vo.ProductVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-12-16
 */
public interface ErrandService extends IService<Errand> {
    List<ErrandVO> findErrandVOByTypeId(Integer level, Integer categoryId);

    ErrandVO findErrandVOById(Integer id);

    List<ErrandVO> findAllErrandVO();
}
