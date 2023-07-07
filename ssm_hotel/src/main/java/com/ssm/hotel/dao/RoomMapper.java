package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Room;
import com.ssm.hotel.vo.RoomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-29 19:14
 */
public interface RoomMapper {
    /**
     * 查询房间列表
     * @param roomVo
     * @return
     */
    List<Room> findRoomListByPage(RoomVo roomVo);

    /**
     * 添加房间
     * @param room
     * @return
     */
    int addRoom(Room room);

    /**
     * 修改房间
     * @param room
     * @return
     */
    int updateRoom(Room room);

    /**
     * 删除房间
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据楼层查询房间的列表
     * @return
     */
    List<Room> findRoomListByFloorId();

    /**
     * 查看房间的详情
     * @param id
     * @return
     */
    Room findById(Integer id);
}
