package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Checkout;

/**
 * @author zystart
 * @create 2023-06-07 15:57
 */
public interface CheckoutMapper {
    /**
     * 添加退房记录
     * @param checkout
     * @return
     */
    int addCheckout(Checkout checkout);
}
