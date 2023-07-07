package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Room;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.RoomService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.vo.RoomVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-29 19:34
 */
@RestController
@RequestMapping("/admin/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    /**
     * 查询房间列表
     * @param roomVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoomVo roomVo){
        //设置分页信息
        PageHelper.startPage(roomVo.getPage(),roomVo.getLimit());
        //调用分页查询的方法
        List<Room> roomList = roomService.findRoomListByPage(roomVo);
        //创建分页对象
        PageInfo<Room> pageInfo = new PageInfo<Room>(roomList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加房间
     * @param room
     * @return
     */
    @RequestMapping("/addRoom")
    public String addRoom(Room room){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用添加房间的方法
        if (roomService.addRoom(room)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }

    /**
     * 修改房间
     * @param room
     * @return
     */
    @RequestMapping("/updateRoom")
    public String updateRoom(Room room){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用修改房间的方法
        if (roomService.updateRoom(room)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }

    /**
     * 删除房间
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用删除房间的方法
        if (roomService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        //将map集合以json格式返回
        return JSON.toJSONString(map);
    }
}
