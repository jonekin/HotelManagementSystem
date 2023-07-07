package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Menu;
import com.ssm.hotel.entity.Role;
import com.ssm.hotel.service.EmployeeService;
import com.ssm.hotel.service.MenuService;
import com.ssm.hotel.service.RoleService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.utils.TreeNode;
import com.ssm.hotel.vo.RoleVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2022-11-21 15:54
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private EmployeeService employeeService;
    
    @Resource
    private MenuService menuService;

    /**
     * 查询角色列表
     * @param roleVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo){
        //设置分页信息
        PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        //调用分页查询的方法
        List<Role> roleList = roleService.findRoleList(roleVo);
        //创建分页对象
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/addRole")
    public String addRole(Role role){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用添加角色方法
        if (roleService.addRole(role) > 0){
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
     * 修改角色
     * @param role
     * @return
     */
    @RequestMapping("/updateRole")
    public String updateRole(Role role){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (roleService.updateRole(role) > 0){
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
     * 检查该角色下是否存在员工信息
     * @param id
     * @return
     */
    @RequestMapping("/checkEmployee")
    public String checkEmployee(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用根据角色名称查询员工数量的方法
        if(employeeService.getEmployeeCountByRoleId(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该角色存在员工信息，无法删除");
        }else {
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用删除部门的方法
        if(roleService.deleteById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据角色ID查询该角色拥有的菜单
     * @param roleId
     * @return
     */
   /* @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(Integer roleId){
        //调用查询菜单列表方法
        List<Menu> menuList = menuService.findMenuList();
        //调用根据角色id查询该角色已拥有的菜单id方法
        List<Integer> menuByRoleId = menuService.findMenuIdListByRoleId(roleId);
        //保存菜单信息
        List<Menu> currentMenus = new ArrayList<>();
        //判断集合中是否存在数据
        if (menuByRoleId != null && menuByRoleId.size() > 0){
            //根据菜单id查询菜单信息
            currentMenus = menuService.findMenuByMenuId(menuByRoleId);
        }
        //创建集合保存树节点信息
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu menu : menuList) {
            //标识是否选中
            String checkArr = "0";//0未选中，1选中
            //内层循环遍历当前角色拥有的权限菜单
            //循环比较的原因：比较两个集合中的数据是否有相同的，有相同的表示当前角色拥有这个权限
            for (Menu currentMenu : currentMenus) {
                if (menu.getId() == currentMenu.getId()){
                    checkArr = "1";
                    break;
                }
            }

            //菜单是否展开
            Boolean spread = (menu.getSpread() == null || menu.getSpread() == 1) ? true : false;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread,checkArr));
        }
        //将数据返回到页面
        return new DataGridViewResult(treeNodes);
    }*/

    @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(Integer roleId){
        //调用查询菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();
         //调用根据角色ID查询该角色已经拥有的菜单ID的方法
        List<Integer> currentRoleMenuIds = menuService.findMenuIdListByRoleId(roleId);
        //定义集合，保存菜单信息
        List<Menu> currentMenus = new ArrayList<Menu>();
        //判断集合是否存在数据
        if(currentRoleMenuIds!=null && currentRoleMenuIds.size()>0){
        //根据菜单ID查询该菜单的信息
            currentMenus = menuService.findMenuByMenuId(currentRoleMenuIds);
        }
        //创建集合保存树节点信息
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //循环遍历所有菜单
        for (Menu menu : menuList) {
            //定义变量，标识是否选中
            String checkArr = "0";//0表示复选框不选中，1表示选中复选框
            //内层循环遍历当前角色拥有的权限菜单
            //循环比较的原因：比较两个集合中的数据是否有相同的，有相同的表示当前角色拥有这个权限
            for (Menu currentMenu : currentMenus) {
                //如果ID相等，表示当前角色有这个菜单，有这个菜单则需要将复选框选中
                if(menu.getId() == currentMenu.getId()){
                    checkArr ="1";//选中
                    break;
                }
            }
            //定义变量，表示菜单是否展开
            Boolean spread = (menu.getSpread()==null || menu.getSpread()==1) ? true :
                    false;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),
                    menu.getTitle(),spread,checkArr));
        }
        //将数据返回到页面
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 分配菜单
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/saveRoleMenu")
    public String saveRoleMenu(String ids,Integer roleId){
        Map<String,Object> map = new HashMap<>();
        //调用保存角色菜单关系的方法
        if (roleService.saveRoleMenu(ids,roleId) > 0){
            map.put("message","菜单分配成功");
        }else {
            map.put("message","菜单分配失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/initRoleListByEmpId")
    public DataGridViewResult initRoleListByEmpId(int id){
        //调用查询所有角色列表的方法
        List<Map<String, Object>> roleListByMap = roleService.findRoleListByMap();
        //根据员工id查询该员工拥有的角色列表
        List<Integer> roleids = roleService.findEmpRoleByEmpId(id);
        //循环比较两个集合中的角色id
        for (Map<String, Object> map : roleListByMap) {
            Boolean flag = false;
            //获取每一个角色id
            int rid = (int) map.get("id");
            //遍历该员工拥有的角色列表
            for (Integer roleid : roleids) {
                if (rid == roleid){
                    flag = true;
                    break;
                }
            }
            //将状态保存到map集合中
            map.put("LAY_CHECKED",flag);
        }
        return new DataGridViewResult(Long.valueOf(roleListByMap.size()),roleListByMap);
    }
}
