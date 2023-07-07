package com.ssm.hotel.controller.front;

import com.alibaba.fastjson.JSON;
import com.ssm.hotel.entity.Orders;
import com.ssm.hotel.service.OrdersService;
import com.ssm.hotel.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zystart
 * @create 2023-06-01 18:36
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    /**
     * 添加订单
     * @param orders
     * @return
     */
    @RequestMapping("/addOrders")
    @ResponseBody
    public String addOrders(Orders orders){
        Map<String, Object> map = new HashMap<>();
        //调用添加订单方法
        if (ordersService.addOrders(orders)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"酒店预定成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"酒店预定失败,请重试");
        }
        return JSON.toJSONString(map);
    }
}
