package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.entity.User;
import com.ssm.hotel.service.UserService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.vo.EmployeeVo;
import com.ssm.hotel.vo.UserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2023-06-07 20:42
 */
@RestController
@RequestMapping("/admin/userManager")
public class UserManagerController {

    @Resource
    private UserService userService;

    /**
     * 用户列表
     * @param userVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(UserVo userVo){
        //设置分页信息
        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        //调用分页查询的方法
        List<User> userList = userService.findUserList(userVo);
        //创建分页对象
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 删除用户
     * @param loginName
     * @return
     */
    @RequestMapping("/deleteByName")
    public String deleteByName(String loginName){
        Map<String, Object> map = new HashMap<String,Object>();
        //调用删除员工的方法
        if(userService.deleteByName(loginName) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 重置密码
     * @param loginName
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(String loginName){
        Map<String, Object> map = new HashMap<String,Object>();
        //调用删除员工的方法
        if(userService.resetPwd(loginName)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"密码重置成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"密码重置失败");
        }
        return JSON.toJSONString(map);
    }
}
