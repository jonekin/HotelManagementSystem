package com.ssm.hotel.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2023-06-07 19:28
 */
public interface ChartsMapper {
    /**
     * 查询每个年度营业额
     * @return
     */
    List<Map> getTotalPriceByYear();

    /**
     * 统计月营业额报表
     * @param year
     * @return
     */
    List<Double> getMonthTotalPriceByYear(@Param("year") String year);
}
