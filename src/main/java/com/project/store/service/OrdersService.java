package com.project.store.service;

import com.project.store.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.vo.OrdersVO;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
public interface OrdersService extends IService<Orders> {

    Integer save(Product product, String useAddress, User user);

    List<OrdersVO> findAllOrdersVOByBuyerID(Integer buyerId);

    List<OrdersVO> findAllOrdersVOByOwnerID(Integer ownerId);
}
