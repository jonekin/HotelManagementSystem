package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.CheckinMapper;
import com.ssm.hotel.dao.OrdersMapper;
import com.ssm.hotel.dao.RoomTypeMapper;
import com.ssm.hotel.entity.Checkin;
import com.ssm.hotel.entity.Orders;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.CheckinService;
import com.ssm.hotel.vo.CheckinVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zystart
 * @create 2023-06-06 16:37
 */
@Service
@Transactional
public class CheckinServiceImpl implements CheckinService {
    @Resource
    private CheckinMapper checkinMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<Checkin> findCheckinList(CheckinVo checkinVo) {
        return checkinMapper.findCheckinList(checkinVo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int addCheckin(Checkin checkin) {
        //登记入住状态
        checkin.setStatus(1);
        checkin.setCreatedate(new Date());
        //1添加入住信息
        int count = checkinMapper.addCheckin(checkin);
        if (count>0){
            //2预定订单的状态
            Orders orders = new Orders();
            orders.setId(checkin.getOrdersid());
            orders.setStatus(3);
            //调用修改订单方法
            ordersMapper.updateOrders(orders);
            //3修改房型表中的已入住数量
            RoomType roomtype = roomTypeMapper.findById(checkin.getRoomtypeid());
            roomtype.setLivednum(roomtype.getLivednum()+1);
            roomTypeMapper.updateRoomType(roomtype);
        }
        return count;
    }
}
