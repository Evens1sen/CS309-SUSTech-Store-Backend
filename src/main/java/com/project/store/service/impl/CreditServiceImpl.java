package com.project.store.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.entity.Credit;
import com.project.store.entity.User;
import com.project.store.mapper.CreditMapper;
import com.project.store.mapper.OrdersMapper;
import com.project.store.mapper.UserMapper;
import com.project.store.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl extends ServiceImpl<CreditMapper, Credit> implements CreditService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int queryCredit(Integer id) {
        User user = userMapper.selectById(id);
        return user.getCredit();
    }

    @Override
    public double getDiscount(Integer id) {
        User user = userMapper.selectById(id);
        int credit = user.getCredit();
        if (credit >= 200) {
            return 0.95;
        }
        return 1.0;
    }

    @Override
    public int updateCredit(Integer id,Double star){
        User user = userMapper.selectById(id);
        user.setCredit(user.getCredit()+(int)(star-4)*2);
        return user.getCredit();
    }

}
