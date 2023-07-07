package com.ssm.hotel.controller.front;

import com.alibaba.fastjson.JSON;
import com.ssm.hotel.entity.User;
import com.ssm.hotel.service.UserService;
import com.ssm.hotel.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author zystart
 * @create 2023-05-30 13:27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user){
        //创建Map集合保存结果信息
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用注册方法
        if (userService.addUser(user)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"注册成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"注册失败,请重新尝试");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }

    /**
     * 检查用户名是否存在
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public String checkName(String loginName){
        //创建Map集合保存结果信息
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用根据用户名查询用户信息的方法
        if (userService.findUserByName(loginName)!=null){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"用户名已存在，请重新输入");
        }else {
            map.put(SystemConstant.EXIST,false);
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }

    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        //创建Map集合保存结果信息
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用登录方法
        User loginUser = userService.login(loginName, password);
        //判断对象是否为空
        if (loginUser!=null){
            map.put(SystemConstant.SUCCESS,true);
            loginUser.setPassword(null);//清空
            //保存用户信息到会话中
            session.setAttribute(SystemConstant.FRONT_LOGIN_USER,loginUser);
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"用户名或密码错误，请重新输入");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }
}
