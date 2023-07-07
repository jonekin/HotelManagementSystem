package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Checkin;
import com.ssm.hotel.vo.CheckinVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-06-06 15:51
 */
public interface CheckinMapper {
    /**
     * 查询入住列表
     * @param checkinVo
     * @return
     */
    List<Checkin> findCheckinList(CheckinVo checkinVo);

    /**
     * 添加入住信息
     * @param checkin
     * @return
     */
    int addCheckin(Checkin checkin);

    /**
     * 根据主键查询入住的id
     * @param checkinid
     * @return
     */
    Checkin findById(Integer checkinid);

    /**
     * 修改入住信息的方法
     * @param checkin
     * @return
     */
    int updateCheckin(Checkin checkin);
}
