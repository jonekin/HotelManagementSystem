package com.ssm.hotel.service;

import com.ssm.hotel.entity.Checkin;
import com.ssm.hotel.vo.CheckinVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-06-06 16:36
 */
public interface CheckinService {
    List<Checkin> findCheckinList(CheckinVo checkinVo);
    int addCheckin(Checkin checkin);
}
