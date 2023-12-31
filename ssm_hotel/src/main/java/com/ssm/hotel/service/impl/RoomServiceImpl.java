package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.RoomMapper;
import com.ssm.hotel.entity.Room;
import com.ssm.hotel.service.RoomService;
import com.ssm.hotel.vo.RoomVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-29 19:33
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<Room> findRoomListByPage(RoomVo roomVo) {
        return roomMapper.findRoomListByPage(roomVo);
    }

    @Override
    public int addRoom(Room room) {
        return roomMapper.addRoom(room);
    }

    @Override
    public int updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    @Override
    public int deleteById(int id) {
        return roomMapper.deleteById(id);
    }

    @Override
    public List<Room> findRoomListByFloorId() {
        return roomMapper.findRoomListByFloorId();
    }

    @Override
    public Room findById(Integer id) {
        return roomMapper.findById(id);
    }
}
