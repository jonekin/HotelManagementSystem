package com.ssm.hotel.service;

import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zystart
 * @create 2022-11-16 23:00
 */
public interface EmployeeService {
    /**
     * 员工登录
     * @param loginName
     * @param loginPwd
     * @return
     */
    Employee login(String loginName, String loginPwd);
    Employee findEmployeeById(Integer id);

    int getEmployeeCountByDeptId(Integer deptId);

    int getEmployeeCountByRoleId(Integer roleId);

    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    int addEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteById(Integer id);
    int resetPwd(int id);

    boolean saveEmployeeRole(String roleIds, Integer empId);
    int updatePassword(int id,String oldPassword,String newPassword,HttpSession session);
}
