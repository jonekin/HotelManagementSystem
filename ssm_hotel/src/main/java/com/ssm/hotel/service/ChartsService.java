package com.ssm.hotel.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2023-06-07 19:37
 */
public interface ChartsService {
    List<Map> getTotalPriceByYear();
    List<Double> getMonthTotalPriceByYear(String year);
}
