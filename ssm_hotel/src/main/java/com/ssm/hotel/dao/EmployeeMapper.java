package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.vo.EmployeeVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zystart
 * @create 2022-11-16 17:14
 */
@Repository
public interface EmployeeMapper {
    //根据账号名称查询员工信息
    Employee findEmployeeByLoginName(@Param("loginName") String loginName);

    /**
     * 根据部门编号查询员工数量
     * @param deptId
     * @return
     */
    int getEmployeeCountByDeptId(Integer deptId);

    /**
     * 根据角色编号查询员工数量
     * @param roleId
     * @return
     */
    int getEmployeeCountByRoleId(Integer roleId);

    /**
     * 查询员工列表
     * @param employeeVo
     * @return
     */
    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    /**
     * 添加员工
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);

    /**
     * 删除员工
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 删除员工关系表数据
     * @param id
     */
    @Delete("delete from sys_role_employee where eid = #{id}")
    void deleteEmploeeAndRole(Integer id);

    /**
     * 保存员工关系数据
     * @param roleId
     * @param empId
     */
    @Insert("insert into sys_role_employee(eid,rid) values(#{eid},#{rid})")
    void addEmployeeRole(@Param("rid") String roleId, @Param("eid") Integer empId);

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param salt
     * @param newPassword
     * @return
     */
    @Update("update sys_employee set loginpwd=#{newPassword},salt=#{salt} where id=#{id} and loginpwd=#{oldPassword}")
    int updatePassword(@Param("id") int id,@Param("oldPassword") String oldPassword,@Param("salt") String salt,@Param("newPassword") String newPassword);

    /**
     * 通过id查询员工信息
     * @param id
     * @return
     */
    Employee findEmployeeById(@Param("id") Integer id);
}
