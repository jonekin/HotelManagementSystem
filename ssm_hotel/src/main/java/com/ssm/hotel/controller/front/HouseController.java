package com.ssm.hotel.controller.front;

import com.ssm.hotel.entity.Room;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.RoomService;
import com.ssm.hotel.service.RoomTypeService;
import com.ssm.hotel.vo.RoomVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-31 20:00
 */
@Controller
@RequestMapping("/house")
public class HouseController {

    @Resource
    private RoomService roomService;

    @Resource
    private RoomTypeService roomTypeService;

    /**
     * 查询房间详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id}.html")
    public String detail(@PathVariable Integer id, Model model){
        //调用查询房间详情的方法
        Room room = roomService.findById(id);
        //将数据放到模型中
        model.addAttribute("room",room);
        return "detail";
    }

    /**
     * 查询所有房间列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //创建查询条件类
        RoomVo roomVo = new RoomVo();
        roomVo.setStatus(3);
        //查询房间列表
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        return "hotelList";
    }

    /**
     * 查询所有房间列表
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/list/{id}")
    public String list(@PathVariable Integer id,Model model){
        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);
        //创建查询条件类
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomtypeid(id);
        roomVo.setStatus(3);
        //查询房间列表
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        model.addAttribute("typeId",id);//将当前选中的房型id保存到模型中
        return "hotelList";
    }
}
