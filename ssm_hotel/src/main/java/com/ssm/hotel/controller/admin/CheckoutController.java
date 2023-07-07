package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.ssm.hotel.dao.CheckoutMapper;
import com.ssm.hotel.entity.Checkin;
import com.ssm.hotel.entity.Checkout;
import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.service.CheckoutService;
import com.ssm.hotel.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author zystart
 * @create 2023-06-07 16:35
 */
@RestController
@RequestMapping("/admin/checkout")
public class CheckoutController {

    @Resource
    private CheckoutService checkoutService;

    /**
     * 办理退房
     * @param checkout
     * @return
     */
    @RequestMapping("/addCheckout")
    public String addCheckout(Checkout checkout, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //获取当前登录用户
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        checkout.setCreatedby(loginUser.getId());
        //调用添加入住信息的方法
        if (checkoutService.addCheckout(checkout)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"办理退房成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"办理退房失败");
        }
        return JSON.toJSONString(map);
    }
}
