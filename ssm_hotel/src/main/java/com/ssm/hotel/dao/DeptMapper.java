package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Dept;
import com.ssm.hotel.vo.DeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
/**
 * @author zystart
 * @create 2022-11-18 22:32
 */
public interface DeptMapper {
    /**
     * 查询部门列表
     * @param deptVo
     * @return
     */
    List<Dept> findDeptListByPage(DeptVo deptVo);

    /**
     * 添加部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);


    /**
     * 删除部门
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 查询所有部门
     * @return
     */
    List<Dept> findDeptList();
}
