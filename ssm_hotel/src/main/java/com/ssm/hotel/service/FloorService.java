package com.ssm.hotel.service;

import com.ssm.hotel.entity.Floor;
import com.ssm.hotel.vo.FloorVo;

import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 14:31
 */
public interface FloorService {
    List<Floor> findFloorList(FloorVo floorVo);
    int addFloor(Floor floor);
    int updateFloor(Floor floor);
}
