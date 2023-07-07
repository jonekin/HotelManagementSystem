package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.ssm.hotel.utils.SystemConstant;
import com.ssm.hotel.utils.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author zystart
 * @create 2023-05-29 20:44
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {

    /**
     * 文件上传
     * @param attach
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "file") MultipartFile attach){
        //创建Map集合保存返回的JSON数据
        HashMap<String, Object> map = new HashMap<>();
        //判断是否有选中文件
        if (!attach.isEmpty()){
            //将获取源文件的名称
            String oldFileName = attach.getOriginalFilename();
            //获取文件的后缀名
            String extension = FilenameUtils.getExtension(oldFileName);
            //重命名旧文件
            String newFileName = UUIDUtils.randomUUID()+"."+extension;
            //为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
            String datePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //组装最终的文件名
            String fileName = datePath+"/"+newFileName;
            //创建文件对象
            //参数1：文件上传地址 参数2：文件名称
            File dest = new File(SystemConstant.IMAGE_UPLOAD_PATH,fileName);
            //判断该文件夹是否存在
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            try {
                //进行文件上传
                attach.transferTo(dest);
                map.put("code",0);//状态码
                map.put("msg","上传成功");//返回消息
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("src","/hotel/show/"+fileName);
                map.put("data",dataMap);//文件数据
                map.put("imagePath",fileName);//隐藏域的值，只保留日期文件夹+重命名后的文件名
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return JSON.toJSONString(map);
    }
}
