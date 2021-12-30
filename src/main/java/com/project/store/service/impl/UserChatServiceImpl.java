package com.project.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.UserChat;
import com.project.store.mapper.UserChatMapper;
import com.project.store.service.UserChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-24
 */
@Service
public class UserChatServiceImpl extends ServiceImpl<UserChatMapper, UserChat> implements UserChatService {
    @Autowired
    private UserChatMapper userChatMapper;

    @Override
    public List<UserChat> findAllUserChatByUserId(Integer buy_id, Integer sell_id) {
        QueryWrapper<UserChat> wrapper = new QueryWrapper<>();
        QueryWrapper<UserChat> wrapper2 = new QueryWrapper<>();
        wrapper.eq("buy_id", buy_id);
        wrapper.eq("sell_id", sell_id);
        wrapper.orderByDesc("create_at");
        wrapper2.eq("buy_id", sell_id);
        wrapper2.eq("sell_id", buy_id);
        wrapper2.orderByDesc("create_at");
        List<UserChat> list = userChatMapper.selectList(wrapper);
        List<UserChat> list2 = userChatMapper.selectList(wrapper2);
        list.addAll(list2);
        list.sort(Comparator.comparing(UserChat::getCreateAt));
        return list;
    }
}
