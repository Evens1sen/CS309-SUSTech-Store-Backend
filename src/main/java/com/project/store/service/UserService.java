package com.project.store.service;

import com.project.store.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.security.GeneralSecurityException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-24
 */
public interface UserService extends IService<User> {

    boolean sendEmail(String email, String text) throws GeneralSecurityException;

    boolean sendNotification(String email) throws GeneralSecurityException;//提醒

    String sendVerification(String email) throws GeneralSecurityException;//验证码

    boolean pay(Integer buyerId, Float price);

}
