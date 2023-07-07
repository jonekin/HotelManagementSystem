package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Dept;
import com.ssm.hotel.service.DeptService;
import com.ssm.hotel.service.EmployeeService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.vo.DeptVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author zystart
 * @create 2022-11-18 22:48
 */
@RestController
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @Resource
    private EmployeeService employeeService;

    /**
     * 查询部门列表
     * @param deptVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo deptVo){
        //设置分页信息
        PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());
        //调用分页查询的方法
        List<Dept> deptList = deptService.findDeptListByPage(deptVo);
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加部门
     * @return
     */
    @RequestMapping("/addDept")
    public String addDept(Dept dept){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用添加部门方法
        if(deptService.addDept(dept) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }

    /**
     * 修改部门
     * @return
     */
    @RequestMapping("/updateDept")
    public String updateDept(Dept dept){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用修改部门方法
        if(deptService.updateDept(dept) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }

    /**
     * 检查该部门下是否存在员工信息
     * @param id
     * @return
     */
    @RequestMapping("/checkEmployee")
    public String checkEmployee(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用根据部门编号查询员工数量的方法
        if(employeeService.getEmployeeCountByDeptId(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该部门存在员工信息，无法删除");
        }else {
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用删除部门的方法
        if(deptService.deleteById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询所有部门
     * @return
     */
    @RequestMapping("/deptList")
    public String deptList(){
        //调用查询所有部门信息并返回到页面
        return JSON.toJSONString(deptService.findDeptList());
    }
}
