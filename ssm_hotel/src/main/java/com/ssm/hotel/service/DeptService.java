package com.ssm.hotel.service;

import com.ssm.hotel.entity.Dept;
import com.ssm.hotel.vo.DeptVo;

import java.util.List;

/**
 * @author zystart
 * @create 2022-11-18 22:46
 */
public interface DeptService {
    List<Dept> findDeptListByPage(DeptVo deptVo);
    int addDept(Dept dept);
    int updateDept(Dept dept);
    int deleteById(Integer id);

    List<Dept> findDeptList();
}
