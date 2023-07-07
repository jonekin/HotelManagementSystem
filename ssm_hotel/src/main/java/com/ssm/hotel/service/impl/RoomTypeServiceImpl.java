package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.RoomTypeMapper;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.RoomTypeService;
import com.ssm.hotel.vo.RoomTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 19:32
 */
@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo) {
        return roomTypeMapper.findRoomTypeList(roomTypeVo);
    }

    @Override
    public int addRoomType(RoomType roomType) {
        //可用房间数默认是全部的房间数
        roomType.setAvilablenum(roomType.getRoomnum());
        roomType.setLivednum(0);//已入住房间数
        return roomTypeMapper.addRoomType(roomType);
    }

    @Override
    public int updateRoomType(RoomType roomType) {
        //可用房间数默认是全部的房间数
        roomType.setAvilablenum(roomType.getRoomnum());
        roomType.setLivednum(0);//已入住房间数
        return roomTypeMapper.updateRoomType(roomType);
    }
}
