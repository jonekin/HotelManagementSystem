package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Checkin;
import com.ssm.hotel.entity.Dept;
import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.service.CheckinService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.vo.CheckinVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author zystart
 * @create 2023-06-06 16:40
 */
@RestController
@RequestMapping("/admin/checkin")
public class CheckinController {
    @Resource
    private CheckinService checkinService;

    /**
     * 查询入住列表
     * @param checkinVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(CheckinVo checkinVo){
        //设置分页信息
        PageHelper.startPage(checkinVo.getPage(),checkinVo.getLimit());
        //调用分页查询的方法
        List<Checkin> checkinList =checkinService.findCheckinList(checkinVo);
        //创建分页对象
        PageInfo<Checkin> pageInfo = new PageInfo<>(checkinList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 登记入住
     * @param checkin
     * @return
     */
    @RequestMapping("/addCheckin")
    public String addCheckin(Checkin checkin, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //获取当前登录用户
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        checkin.setCreatedby(loginUser.getId());
        //调用添加入住信息的方法
        if (checkinService.addCheckin(checkin)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"办理入住成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"办理入住失败");
        }
        return JSON.toJSONString(map);
    }
}
