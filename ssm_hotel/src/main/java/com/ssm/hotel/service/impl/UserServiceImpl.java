package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.UserMapper;
import com.ssm.hotel.entity.User;
import com.ssm.hotel.service.UserService;
import com.ssm.hotel.utils.PasswordUtil;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.utils.UUIDUtils;
import com.ssm.hotel.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-30 13:19
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public int addUser(User user) {
        //自动生成盐值
        user.setSalt(UUIDUtils.randomUUID());
        //密码加密
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(), SystemConstant.PASSWORD_COUNT));
        return userMapper.addUser(user);
    }

    @Override
    public User findUserByName(String loginName) {
        return userMapper.findUserByName(loginName);
    }

    @Override
    public User login(String loginName, String password) {
        //调用查询用户信息的方法
        User loginUser = userMapper.findUserByName(loginName);
        //判断对象是否为空
        if (loginUser!=null){
            //密码加密
            String newPassword = PasswordUtil.md5(password,loginUser.getSalt(),SystemConstant.PASSWORD_COUNT);
            //比较密码是否相等
            if (loginUser.getPassword().equals(newPassword)){
                return loginUser;
            }
        }
        return null;
    }

    @Override
    public List<User> findUserList(UserVo userVo) {
        return userMapper.findUserList(userVo);
    }

    @Override
    public int deleteByName(String loginName) {
        return userMapper.deleteByName(loginName);
    }

    @Override
    public int resetPwd(String loginName) {
        User user = new User();
        user.setSalt(UUIDUtils.randomUUID());
        user.setPassword(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,user.getSalt(),SystemConstant.PASSWORD_COUNT));
        user.setLoginName(loginName);
        return userMapper.updateUser(user);
    }
}
