package com.ssm.hotel.service;

import com.ssm.hotel.entity.User;
import com.ssm.hotel.vo.UserVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-30 13:19
 */
public interface UserService {
    int addUser(User user);
    User findUserByName(String loginName);

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    User login(String loginName,String password);
    List<User> findUserList(UserVo userVo);
    int deleteByName(String loginName);
    int resetPwd(String loginName);
}
