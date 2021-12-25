package com.project.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.Errand;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.mapper.ErrandMapper;
import com.project.store.mapper.UserMapper;
import com.project.store.service.ErrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.vo.ErrandVO;
import com.project.store.vo.ProductVO;
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
 * @since 2021-12-16
 */
@Service
public class ErrandServiceImpl extends ServiceImpl<ErrandMapper, Errand> implements ErrandService {

    @Autowired
    private ErrandMapper errandMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ErrandVO> findErrandVOByTypeId(Integer level, Integer categoryId) {
        return null;
    }

    @Override
    public ErrandVO findErrandVOById(Integer id) {
        return null;
    }

    @Override
    public List<ErrandVO> findAllErrandVO() {
        List<ErrandVO> errandVOList = new ArrayList<>();
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        List<Errand> errandList = errandMapper.selectList(wrapper);
        for (Errand errand : errandList) {
            ErrandVO errandVO = new ErrandVO();
            User owner = userMapper.selectById(errand.getOwnerId());
            BeanUtils.copyProperties(errand, errandVO);
            errandVO.setOwnerNickname(owner.getNickName());
            errandVO.setOwnerIcon(owner.getIcon());
            errandVOList.add(errandVO);
        }

        return errandVOList;
    }
}
