package com.project.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.entity.Credit;

/**
 * @author ZMT
 */

public interface CreditService extends IService<Credit> {
    int queryCredit(Integer id);
    double getDiscount(Integer id);
    int updateCredit(Integer id,Double star);
}