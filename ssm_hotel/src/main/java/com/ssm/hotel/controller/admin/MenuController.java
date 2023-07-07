package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.entity.Menu;
import com.ssm.hotel.service.MenuService;
import com.ssm.hotel.utils.*;
import com.ssm.hotel.vo.MenuVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author zystart
 * @create 2022-11-18 15:12
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 加载首页左侧菜单导航
     * @return
     */
    @RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session){
        //创建Map集合，保存MenuInfo信息
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        //创建Map集合，保存homeInfo信息
        Map<String, Object> homeInfo = new LinkedHashMap<String, Object>();
        //创建Map集合，保存logoInfo信息
        Map<String, Object> logoInfo = new LinkedHashMap<String, Object>();

        //调用查询所有菜单列表的方法
        //List<Menu> menuList = menuService.findMenuList();//该方法登录的角色能看到所有的功能模块

        //获取当前登录用户
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        //根据当前登录用户角色，动态显示菜单列表
        List<Menu> menuList = menuService.findMenuListByEmployeeId(employee.getId());
        //创建集合，保存菜单关系
        List<MenuNode> menuNodeList = new ArrayList<MenuNode>();
        //循环遍历菜单列表，目的是创建菜单之间的层级关系
        for (Menu menu : menuList) {
            //创建菜单节点对象
            MenuNode menuNode = new MenuNode();
            menuNode.setHref(menu.getHref());
            menuNode.setIcon(menu.getIcon());
            menuNode.setId(menu.getId());
            menuNode.setPid(menu.getPid());
            menuNode.setSpread(menu.getSpread());
            menuNode.setTarget(menu.getTarget());
            menuNode.setTitle(menu.getTitle());
            //将对象添加到集合
            menuNodeList.add(menuNode);
        }

        //保存homeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/desktop");
        //保存logoInfo信息
        logoInfo.put("title","酒店管理系统");
        logoInfo.put("image","/statics/layui/images/logo.png");
        logoInfo.put("href","/admin/home.html");//首页地址

        //将菜单信息添加到menuInfo中
        map.put("menuInfo", TreeUtil.toTree(menuNodeList,0));
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);

        return JSON.toJSONString(map);
    }

    /**
     * 加载菜单管理页面左侧导航树
     * @return
     */
    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        //调用查询所有菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();
        //创建集合保存节点信息
        List<TreeNode> treeNodes = new ArrayList<>();
        //循环遍历菜单列表集合
        for (Menu menu: menuList) {
            //判断当前菜单是否展开
            Boolean spread = (menu.getSpread()==null || menu.getSpread()==1)?true:false;
            //将菜单信息保存到treeNodes集合中
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }
        //返回数据
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 分页查询菜单列表
     * @param menuVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(MenuVo menuVo){
        //设置分页信息
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        //调用查询菜单列表的方法
        List<Menu> menuListByPage = menuService.findMenuListByPage(menuVo);
        //创建分页对象
        PageInfo<Menu> pageInfo = new PageInfo<>(menuListByPage);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    public String add(Menu menu){
        HashMap<String, Object> map = new HashMap<>();
        //调用新增菜单的方法
        if(menuService.addMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @RequestMapping("/updateMenu")
    public String updateMenu(Menu menu){
        HashMap<String, Object> map = new HashMap<>();
        //调用修改菜单的方法
        if(menuService.updateMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        HashMap<String, Object> map = new HashMap<>();
        //调用删除菜单的方法
        if(menuService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 判断该菜单是否有子菜单
     * @param id
     * @return
     */
    @RequestMapping("/checkMenuHasChild")
    public String checkMenuHasChild(Integer id){
        HashMap<String, Object> map = new HashMap<>();
        //调用查询菜单的方法
        if(menuService.getMenuCountByMenuId(id)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该菜单下有子菜单，无法删除");
        }else {
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }
}
