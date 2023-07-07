package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.RoleMapper;
import com.ssm.hotel.entity.Role;
import com.ssm.hotel.service.RoleService;
import com.ssm.hotel.vo.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2022-11-21 15:50
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleList(RoleVo roleVo) {
        return roleMapper.findRoleList(roleVo);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }
    @Override
    public int saveRoleMenu(String ids, Integer roleId) {
        try {
            //删除原有的菜单关系
            roleMapper.deleteRoleMenu(roleId);
            //将ids拆分成数组
            String[] idsStr = ids.split(",");
            //循环调用
            for (int i = 0; i < idsStr.length; i++) {
                roleMapper.addRoleMenu(roleId,idsStr[i]);
            }
            return 1;//成功
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;//失败
    }

    @Override
    public List<Map<String, Object>> findRoleListByMap() {
        return roleMapper.findRoleListByMap();
    }

    @Override
    public List<Integer> findEmpRoleByEmpId(Integer empId) {
        return roleMapper.findEmpRoleByEmpId(empId);
    }
}
