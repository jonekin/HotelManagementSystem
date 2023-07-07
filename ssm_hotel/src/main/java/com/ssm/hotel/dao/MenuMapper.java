package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Menu;
import com.ssm.hotel.vo.MenuVo;

import java.util.List;

/**
 * @author zystart
 * @create 2022-11-18 15:05
 */
public interface MenuMapper {
    /**
     * 查询所有菜单列表
     * @return
     */
    List<Menu> findMenuList();

    /**
     * 调用根据角色id查询该角色已拥有的菜单
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdListByRoleId(int roleId);

    /**
     * 根据菜单编号查询菜单信息
     * @param currentRoleMenuIds
     * @return
     */
    List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds);

    /**
     * 查询菜单列表
     * @param menuVo
     * @return
     */
    List<Menu> findMenuListByPage(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据菜单id查询该菜单是否有子菜单
     * @param id
     * @return
     */
    int getMenuCountByMenuId(Integer id);

    /**
     * 根据当前登录的员工查询菜单列表
     * @param employeeId
     * @return
     */
    List<Menu> findMenuListByEmployeeId(Integer employeeId);
}
