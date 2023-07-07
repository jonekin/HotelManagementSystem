package com.ssm.hotel.service;

import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.vo.RoomTypeVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 19:32
 */
public interface RoomTypeService {
    List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);
    int addRoomType(RoomType roomType);
    int updateRoomType(RoomType roomType);
}
