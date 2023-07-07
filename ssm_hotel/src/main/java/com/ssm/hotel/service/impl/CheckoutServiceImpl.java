package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.*;
import com.ssm.hotel.entity.*;
import com.ssm.hotel.service.CheckoutService;
import com.ssm.hotel.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zystart
 * @create 2023-06-07 16:11
 */
@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    @Resource
    private CheckoutMapper checkoutMapper;
    @Resource
    private CheckinMapper checkinMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private RoomMapper roomMapper;

    @Override
    public int addCheckout(Checkout checkout) {
        checkout.setCreatedate(new Date());
        checkout.setCheckoutnumber(UUIDUtils.randomUUID());
        int count = checkoutMapper.addCheckout(checkout);
        if (count>0){
            Checkin checkin = checkinMapper.findById(checkout.getCheckinid());
            checkin.setStatus(2);
            //调用修改入住订单的方法
            checkinMapper.updateCheckin(checkin);

            Orders orders = new Orders();
            orders.setStatus(4);//订单已完成
            orders.setId(checkin.getOrdersid());
            //调用修改订单的方法
            ordersMapper.updateOrders(orders);

            RoomType roomType = roomTypeMapper.findById(checkin.getRoomtypeid());
            roomType.setAvilablenum(roomType.getAvilablenum()+1);
            roomType.setLivednum(roomType.getLivednum()-1);
            //调用修改房型的方法
            roomTypeMapper.updateRoomType(roomType);

            Room room = new Room();
            room.setId(checkin.getRoomid().intValue());
            room.setStatus(3);//可预订
            roomMapper.updateRoom(room);
        }
        return count;
    }
}
