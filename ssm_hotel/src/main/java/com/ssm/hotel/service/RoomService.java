package com.ssm.hotel.service;

import com.ssm.hotel.entity.Room;
import com.ssm.hotel.vo.RoomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-29 19:32
 */
public interface RoomService {
    List<Room> findRoomListByPage(RoomVo roomVo);
    int addRoom(Room room);
    int updateRoom(Room room);
    int deleteById(int id);
    List<Room> findRoomListByFloorId();
    Room findById(Integer id);
}
