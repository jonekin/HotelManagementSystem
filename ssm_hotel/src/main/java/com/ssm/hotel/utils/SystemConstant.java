package com.ssm.hotel.utils;

/**
 * @author zystart
 * @create 2022-11-16 22:18
 */
public interface SystemConstant {
    /**
     * 加密次数
     */
    Integer PASSWORD_COUNT = 5;
    /**
     * 登录用户保存的key
     */
    String LOGINUSER = "loginUser";
    /**
     * 成功
     */
    String SUCCESS = "success";
    /**
     * 验证提示信息
     */
    String MESSAGE = "message";
    /**
     * 是否存在
     */
    String EXIST = "exist";
    /**
     * 默认密码
     */
    String DEFAULT_LOGIN_PWD = "123456";

    /**
     * 图片上传的地址
     */
    String IMAGE_UPLOAD_PATH = "C:\\Users\\Administrator\\IdeaProjects\\ssm_hotel\\upload\\";

    /**
     *前台用户登录时保存的key
     */
    String FRONT_LOGIN_USER = "currentUser";

}
