package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.OrdersMapper;
import com.ssm.hotel.dao.RoomMapper;
import com.ssm.hotel.dao.RoomTypeMapper;
import com.ssm.hotel.entity.Orders;
import com.ssm.hotel.entity.Room;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.OrdersService;
import com.ssm.hotel.service.RoomTypeService;
import com.ssm.hotel.utils.UUIDUtils;
import com.ssm.hotel.vo.OrdersVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2023-06-01 18:29
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int addOrders(Orders orders) {
        orders.setStatus(1);
        orders.setOrdersno(UUIDUtils.randomUUID());
        int count = ordersMapper.addOrders(orders);
        if (count>0){
            //修改房间信息
            Room room = roomMapper.findById(orders.getRoomid());
            room.setStatus(1);
            roomMapper.updateRoom(room);
            //修改房型信息
            RoomType roomType = roomTypeMapper.findById(orders.getRoomtypeid());
            roomType.setAvilablenum(roomType.getAvilablenum()-1);
            roomType.setReservednum(roomType.getReservednum()+1);
            roomTypeMapper.updateRoomType(roomType);
        }
        return count;
    }

    @Override
    public List<Orders> findOrdersList(OrdersVo ordersVo) {
        return ordersMapper.findOrdersList(ordersVo);
    }

    @Override
    public int updateOrders(Orders orders) {
        return ordersMapper.updateOrders(orders);
    }
}
