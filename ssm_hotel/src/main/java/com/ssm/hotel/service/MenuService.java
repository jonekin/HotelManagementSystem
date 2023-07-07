package com.ssm.hotel.service;

import com.ssm.hotel.entity.Menu;
import com.ssm.hotel.vo.MenuVo;

import java.util.List;

/**
 * @author zystart
 * @create 2022-11-18 15:09
 */
public interface MenuService {
    List<Menu> findMenuList();
    List<Integer> findMenuIdListByRoleId(int roleId);
    List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds);
    List<Menu> findMenuListByPage(MenuVo menuVo);
    int addMenu(Menu menu);
    int updateMenu(Menu menu);
    int deleteById(int id);
    int getMenuCountByMenuId(Integer id);
    List<Menu> findMenuListByEmployeeId(Integer employeeId);
}
