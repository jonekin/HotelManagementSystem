package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.hotel.entity.Floor;
import com.ssm.hotel.entity.RoomType;
import com.ssm.hotel.service.RoomTypeService;
import com.ssm.hotel.utils.DataGridViewResult;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.utils.UUIDUtils;
import com.ssm.hotel.vo.FloorVo;
import com.ssm.hotel.vo.RoomTypeVo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zystart
 * @create 2023-05-28 19:34
 */
@RestController
@RequestMapping("/admin/roomType")
public class RoomTypeController {
    @Resource
    private RoomTypeService roomTypeService;

    /**
     * 查询房型列表
     * @param roomTypeVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoomTypeVo roomTypeVo){
        //设置分页信息
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
        //调用分页查询的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(roomTypeVo);
        //创建分页对象
        PageInfo<RoomType> pageInfo = new PageInfo<>(roomTypeList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加房型
     * @param roomType
     * @return
     */
    @RequestMapping("/addRoomType")
    public String addRoomType(RoomType roomType){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用添加房型的方法
        if (roomTypeService.addRoomType(roomType)>0){
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
     * 修改房型
     * @param roomType
     * @return
     */
    @RequestMapping("/updateRoomType")
    public String updateRoomType(RoomType roomType){
        HashMap<String, Object> map = new HashMap<String, Object>();
        //调用修改房型的方法
        if (roomTypeService.updateRoomType(roomType)>0){
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
     * 查询所有房间类型
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(roomTypeService.findRoomTypeList(null));
    }
}
