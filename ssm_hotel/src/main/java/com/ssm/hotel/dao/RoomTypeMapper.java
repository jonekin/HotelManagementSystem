package com.ssm.hotel.dao;

import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.vo.RoomTypeVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 19:23
 */
public interface RoomTypeMapper {
    /**
     * 查询房型列表
     * @param roomTypeVo
     * @return
     */
    List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);

    /**
     * 添加房型
     * @param roomType
     * @return
     */
    int addRoomType(RoomType roomType);

    /**
     * 修改房型
     * @param roomType
     * @return
     */
    int updateRoomType(RoomType roomType);

    /**
     * 根据房型id查询房间信息
     * @param roomtypeid
     * @return
     */
    @Select("select * from t_room_type where id = #{id}")
    RoomType findById(Integer roomtypeid);
}
