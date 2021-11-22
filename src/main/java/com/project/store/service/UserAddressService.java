package com.project.store.service;

import com.project.store.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface UserAddressService extends IService<UserAddress> {

    List<UserAddress> findAllUserAddressByUserId(Integer uid);

}
