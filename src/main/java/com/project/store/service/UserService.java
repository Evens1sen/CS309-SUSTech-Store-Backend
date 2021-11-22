package com.project.store.service;

import com.project.store.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface UserService extends IService<User> {

    boolean payByOwnerId(Integer buyerId, Integer ownerId, Float price);
}
