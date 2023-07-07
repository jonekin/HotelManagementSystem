package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Orders;
import com.ssm.hotel.vo.OrdersVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-06-01 18:27
 */
public interface OrdersMapper {

    /**
     * 添加订单
     * @param orders
     * @return
     */
    int addOrders(Orders orders);

    /**
     * 查询订单列表
     * @param ordersVo
     * @return
     */
    List<Orders> findOrdersList(OrdersVo ordersVo);

    /**
     * 修改订单
     * @param orders
     * @return
     */
    int updateOrders(Orders orders);
}
