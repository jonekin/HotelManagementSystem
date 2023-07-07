package com.ssm.hotel.controller.front;

import com.ssm.hotel.entity.Floor;
import com.ssm.hotel.entity.Room;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.FloorService;
import com.ssm.hotel.service.RoomService;
import com.ssm.hotel.service.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-30 20:03
 */
@Controller
public class IndexController {

    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private FloorService floorService;

    @Resource
    private RoomService roomService;

    @RequestMapping({"/","/index.html"})
    public String index(Model model){
        //调用查询房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //调用查询楼层列表的方法
        List<Floor> floorList = floorService.findFloorList(null);

        //调用查询每个楼层的房间列表
        List<Room> roomList = roomService.findRoomListByFloorId();
        model.addAttribute("roomList",roomList);
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorList",floorList);
        return "forward:/home.jsp";
    }
}
