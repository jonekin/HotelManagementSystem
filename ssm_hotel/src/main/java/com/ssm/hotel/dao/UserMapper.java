package com.ssm.hotel.dao;

import com.ssm.hotel.entity.User;
import com.ssm.hotel.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-30 13:13
 */
public interface UserMapper {
    /**
     * 注册（添加用户)
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    User findUserByName(String loginName);

    /**
     * 查询用户列表
     * @param userVo
     * @return
     */
    List<User> findUserList(UserVo userVo);

    /**
     * 删除用户
     * @param loginName
     * @return
     */
    int deleteByName(@Param("loginName") String loginName);

    int updateUser(User user);
}
