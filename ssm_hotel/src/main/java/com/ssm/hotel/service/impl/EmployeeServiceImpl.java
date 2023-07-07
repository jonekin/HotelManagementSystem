package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.EmployeeMapper;
import com.ssm.hotel.entity.Employee;
import com.ssm.hotel.service.EmployeeService;
import com.ssm.hotel.utils.PasswordUtil;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.utils.UUIDUtils;
import com.ssm.hotel.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author zystart
 * @create 2022-11-16 22:11
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param loginName
     * @param loginPwd
     * @return
     */
    public Employee login(String loginName, String loginPwd) {
        //调用根据账号查询员工信息的方法
        Employee employee = employeeMapper.findEmployeeByLoginName(loginName);
        //判断对象是否为空
        if(employee != null){
            //将密码加密处理
            String newPassword = PasswordUtil.md5(loginPwd,employee.getSalt(), SystemConstant.PASSWORD_COUNT);
            //比较密码是否一致
            if(employee.getLoginPwd().equals(newPassword)){
                return employee;//登录成功
            }
        }
        return null;//登录失败
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeMapper.findEmployeeById(id);
    }

    @Override
    public int getEmployeeCountByDeptId(Integer deptId) {
        return employeeMapper.getEmployeeCountByDeptId(deptId);
    }

    @Override
    public int getEmployeeCountByRoleId(Integer roleId) {
        return employeeMapper.getEmployeeCountByRoleId(roleId);
    }

    @Override
    public List<Employee> findEmployeeList(EmployeeVo employeeVo) {
        return employeeMapper.findEmployeeList(employeeVo);
    }

    @Override
    public int addEmployee(Employee employee) {
        employee.setSalt(UUIDUtils.randomUUID());//加密盐值
//        employee.setCreateDate(new Date());//创建时间
        //默认密码
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        //修改时间
        employee.setModifyDate(new Date());
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteById(Integer id) {
        //删除员工关系表数据
        employeeMapper.deleteEmploeeAndRole(id);
        //删除员工
        return employeeMapper.deleteById(id);
    }

    @Override
    public int resetPwd(int id) {
        Employee employee = new Employee();
        employee.setSalt(UUIDUtils.randomUUID());
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        employee.setId(id);//主键，员工编号
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public boolean saveEmployeeRole(String roleIds, Integer empId) {
        try {
            //先删除员工关系表的数据
            employeeMapper.deleteEmploeeAndRole(empId);
            //再保存员工角色关系
            String[] idStr = roleIds.split(",");
            for (int i = 0; i < idStr.length; i++) {
                employeeMapper.addEmployeeRole(idStr[i],empId);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updatePassword(int id, String oldPassword, String newPassword,HttpSession session) {
        Employee employee1 = (Employee)session.getAttribute(SystemConstant.LOGINUSER);
        oldPassword=employee1.getLoginPwd();
        Employee employee = new Employee();
        employee.setSalt(UUIDUtils.randomUUID());
        newPassword=PasswordUtil.md5(newPassword,employee.getSalt(),SystemConstant.PASSWORD_COUNT);

        return employeeMapper.updatePassword(id,oldPassword,employee.getSalt(),newPassword);
    }


}
