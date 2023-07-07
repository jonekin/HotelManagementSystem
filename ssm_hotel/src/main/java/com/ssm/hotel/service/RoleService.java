package com.ssm.hotel.service;

import com.ssm.hotel.entity.Role;
import com.ssm.hotel.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2022-11-21 15:50
 */
public interface RoleService {
    List<Role> findRoleList(RoleVo roleVo);
    int addRole(Role role);
    int updateRole(Role role);
    int deleteById(Integer id);
    int saveRoleMenu(String ids, Integer roleId);
    List<Map<String,Object>> findRoleListByMap();
    List<Integer> findEmpRoleByEmpId(Integer empId);
}
