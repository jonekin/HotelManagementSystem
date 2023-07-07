package com.ssm.hotel.controller.admin;

import com.alibaba.fastjson.JSON;
import com.ssm.hotel.service.ChartsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2023-06-07 19:39
 */
@RestController
@RequestMapping("/admin/charts")
public class ChartsController {
    @Resource
    private ChartsService chartsService;

    @RequestMapping("/getYearTotalPrice")
    public String getYearTotalPrice(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map> mapList = chartsService.getTotalPriceByYear();
        List<String> keyList = new ArrayList<>();//年份
        List<Double> valueList = new ArrayList<>();//营业额

        for (Map m:mapList) {
            keyList.add(m.get("years").toString());
            valueList.add(Double.valueOf(m.get("money").toString()));
        }
        map.put("keyList",keyList);
        map.put("valueList",valueList);

        return JSON.toJSONString(map);
    }

    /**
     * 统计月营业额报表
     * @param year
     * @return
     */
    @RequestMapping("/getMonthTotalPrice")
    public String getMonthTotalPrice(String year){
        List<Double> list = chartsService.getMonthTotalPriceByYear(year);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)==null){
                list.set(i,0D);
            }
        }
        return JSON.toJSONString(list);
    }
}
