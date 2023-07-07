package com.ssm.hotel.service;

import com.ssm.hotel.entity.Orders;
import com.ssm.hotel.vo.OrdersVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-06-01 18:29
 */
public interface OrdersService {
    int addOrders(Orders orders);
    List<Orders> findOrdersList(OrdersVo ordersVo);

    int updateOrders(Orders orders);
}
