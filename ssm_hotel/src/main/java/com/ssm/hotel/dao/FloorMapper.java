package com.ssm.hotel.dao;

import com.ssm.hotel.entity.Floor;
import com.ssm.hotel.vo.FloorVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 14:25
 */
public interface FloorMapper {
    /**
     * 查询楼层列表
     * @param floorVo
     * @return
     */
    List<Floor> findFloorList(FloorVo floorVo);

    /**
     * 添加楼层
     * @param floor
     * @return
     */
    int addFloor(Floor floor);

    /**
     * 修改楼层
     * @param floor
     * @return
     */
    int updateFloor(Floor floor);

}
