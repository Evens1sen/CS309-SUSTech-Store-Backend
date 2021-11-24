package com.project.store.service;

import com.project.store.entity.UserChat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-24
 */
public interface UserChatService extends IService<UserChat> {
    List<UserChat> findAllUserChatByUserId(Integer buy_id, Integer sell_id);

}
