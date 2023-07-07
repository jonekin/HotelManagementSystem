package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.MenuMapper;
import com.ssm.hotel.entity.Menu;
import com.ssm.hotel.service.MenuService;
import com.ssm.hotel.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2022-11-18 15:10
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> findMenuList() {
        return menuMapper.findMenuList();
    }

    @Override
    public List<Integer> findMenuIdListByRoleId(int roleId) {
        return menuMapper.findMenuIdListByRoleId(roleId);
    }

    @Override
    public List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds) {
        return menuMapper.findMenuByMenuId(currentRoleMenuIds);
    }

    @Override
    public List<Menu> findMenuListByPage(MenuVo menuVo) {
        return menuMapper.findMenuListByPage(menuVo);
    }

    @Override
    public int addMenu(Menu menu) {
        //如果没有选择父级菜单，默认为0
        if (menu.getPid()==null){
            menu.setPid(0);
        }
        menu.setTarget("_self");
        return menuMapper.addMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteById(int id) {
        return menuMapper.deleteById(id);
    }

    @Override
    public int getMenuCountByMenuId(Integer id) {
        return menuMapper.getMenuCountByMenuId(id);
    }

    @Override
    public List<Menu> findMenuListByEmployeeId(Integer employeeId) {
        return menuMapper.findMenuListByEmployeeId(employeeId);
    }
}
