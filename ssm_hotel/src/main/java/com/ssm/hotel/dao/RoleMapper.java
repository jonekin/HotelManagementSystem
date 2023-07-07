package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Role;
import com.ssm.hotel.vo.RoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2022-11-21 15:45
 */
@Mapper
public interface RoleMapper {
    /**
     * 查询角色列表
     * @param roleVo
     * @return
     */
    List<Role> findRoleList(RoleVo roleVo);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 删除原有的关系
     * @param roleId
     */
    @Delete("delete from sys_role_menu where rid = #{roleId}")
    void deleteRoleMenu(Integer roleId);

    /**
     * 添加角色菜单关系数据
     * @param roleId
     * @param menuId
     */
    @Insert("insert into sys_role_menu(mid,rid) values(#{menuId},#{roleId})")
    void addRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") String menuId);

    /**
     * 查询所有角色列表
     * @return
     */
    List<Map<String,Object>> findRoleListByMap();

    /**
     * 根据员工id查询该员工拥有的角色列表
     * @param empId
     * @return
     */
    List<Integer> findEmpRoleByEmpId(Integer empId);
}
