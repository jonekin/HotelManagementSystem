package com.ssm.hotel.controller.admin;

import com.ssm.hotel.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author zystart
 * @create 2022-11-16 21:41
 */
@Controller
@RequestMapping("/admin")
public class SystemController {

    /**
     * 转发到登录页
     * @return
     */
    @RequestMapping("/login.html")
    public String login(){
        return "admin/login";
    }

    /**
     * 转发到后台首页
     * @return
     */
    @RequestMapping("/home.html")
    public String home(){
        return "admin/home";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(SystemConstant.LOGINUSER);
        /*session.invalidate();*/
        //重定向到登录页面
        return "redirect:/admin/login.html";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping("/changePwd")
    public String changePwd(){
        return "admin/modPassword";
    }

    /**
     * 基本信息
     * @return
     */
    @RequestMapping("/empInfo")
    public String basicInfo(){
        return "admin/empInfo";
    }

    /**
     *后台初始访问页面
     * @return
     */
    @RequestMapping("/desktop")
    public String desktop(){
        return "admin/desktop";
    }

    /**
     * 转发到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager(){
        return "admin/dept/deptManager";
    }

    /**
     * 转发到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager(){
        return "admin/role/roleManager";
    }

    /**
     * 转发到员工管理页面
     * @return
     */
    @RequestMapping("/toEmployeeManager")
    public String toEmployeeManager(){
        return "admin/emp/employeeManager";
    }

    /**
     * 去到菜单管理界面
     * @return
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager(){
        return "admin/menu/menuManager";
    }

    /**
     * 去到楼层管理界面
     * @return
     */
    @RequestMapping("/toFloorManager")
    public String toFloorManager(){
        return "admin/floor/floorManager";
    }

    /**
     * 去到房型管理界面
     * @return
     */
    @RequestMapping("/toRoomTypeManager")
    public String toRoomTypeManager(){
        return "admin/roomType/roomTypeManager";
    }

    /**
     * 去到房间管理界面
     * @return
     */
    @RequestMapping("/toRoomManager")
    public String toRoomManager(){
        return "admin/room/roomManager";
    }

    /**
     * 去到房间管理界面
     * @return
     */
    @RequestMapping("/toOrdersManager")
    public String toOrdersManager(){
        return "admin/orders/ordersManager";
    }

    /**
     * 去到入住管理界面
     * @return
     */
    @RequestMapping("/toCheckinManager")
    public String toCheckinManager(){
        return "admin/checkin/checkinManager";
    }

    /**
     * 去到年度营业额报表统计分析页
     * @return
     */
    @RequestMapping("/toYearTotalPriceManager")
    public String toYearTotalPriceManager(){
        return "admin/charts/yearTotalPriceCharts";
    }

    /**
     * 去到月营业额报表统计分析页
     * @return
     */
    @RequestMapping("/toYearOfMonthCharts")
    public String toYearOfMonthCharts(){
        return "admin/charts/yearOfMonthCharts";
    }

    /**
     * 去到用户管理页
     * @return
     */
    @RequestMapping("/toUserManager")
    public String toUserManager(){
        return "admin/user/userManager";
    }
}
