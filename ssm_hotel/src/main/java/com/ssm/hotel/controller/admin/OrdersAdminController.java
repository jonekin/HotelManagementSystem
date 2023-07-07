package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Orders;
import com.ssm.hotel.entity.Room;
import com.ssm.hotel.service.OrdersService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.vo.OrdersVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author zystart
 * @create 2023-06-04 21:16
 */
@RestController
@RequestMapping("/admin/orders")
public class OrdersAdminController {

    @Resource
    private OrdersService ordersService;

    /**
     * 查询订单列表
     * @param ordersVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(OrdersVo ordersVo){
        //设置分页信息
        PageHelper.startPage(ordersVo.getPage(),ordersVo.getLimit());
        //调用分页查询的方法
        List<Orders> ordersList = ordersService.findOrdersList(ordersVo);
        //创建分页对象
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 确认订单
     * @param orders
     * @return
     */
    @RequestMapping("/confirmOrders")
    public String confirmOrders(Orders orders){
        HashMap<String, Object> map = new HashMap<>();
        orders.setStatus(2);
        //调用修改订单的方法
        if(ordersService.updateOrders(orders)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"订单确认成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"订单确认失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 批量确认订单
     * @param ids
     * @return
     */
    @RequestMapping("/batchConfirm")
    public String batchConfirm(String ids){
        HashMap<String, Object> map = new HashMap<>();
        int count = 0;
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Orders orders = new Orders();
            orders.setStatus(2);
            orders.setId(Integer.valueOf(idsStr[i]));
            count = ordersService.updateOrders(orders);

            if (count>0){
                map.put(SystemConstant.SUCCESS,true);
                map.put(SystemConstant.MESSAGE,"订单确认成功");
            }
        }
        if (count<0){
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"订单确认失败");
        }
        return JSON.toJSONString(map);
    }
}
