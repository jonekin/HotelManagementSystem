package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.FloorMapper;
import com.ssm.hotel.entity.Floor;
import com.ssm.hotel.service.FloorService;
import com.ssm.hotel.vo.FloorVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 14:32
 */
@Service
@Transactional
public class FloorServiceImpl implements FloorService {
    @Resource
    private FloorMapper floorMapper;

    @Override
    public List<Floor> findFloorList(FloorVo floorVo) {
        return floorMapper.findFloorList(floorVo);
    }

    @Override
    public int addFloor(Floor floor) {
        return floorMapper.addFloor(floor);
    }

    @Override
    public int updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }
}
