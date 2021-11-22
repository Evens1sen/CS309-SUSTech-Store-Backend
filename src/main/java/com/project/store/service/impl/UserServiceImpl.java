package com.project.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.User;
import com.project.store.mapper.UserMapper;
import com.project.store.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean payByOwnerId(Integer buyerId, Integer ownerId, Float price) {
        User buyer = userMapper.selectById(buyerId);
        User owner = userMapper.selectById(ownerId);
        buyer.setBalance(buyer.getBalance() - price);
        owner.setBalance(owner.getBalance() + price);
        userMapper.updateById(buyer);
        userMapper.updateById(owner);
        return true;
    }
}
