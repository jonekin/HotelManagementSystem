package com.ssm.hotel.service.impl;

import com.ssm.hotel.dao.ChartsMapper;
import com.ssm.hotel.service.ChartsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zystart
 * @create 2023-06-07 19:38
 */
@Service
@Transactional
public class ChartsServiceImpl implements ChartsService {
    @Resource
    private ChartsMapper chartsMapper;

    @Override
    public List<Map> getTotalPriceByYear() {
        return chartsMapper.getTotalPriceByYear();
    }

    @Override
    public List<Double> getMonthTotalPriceByYear(String year) {
        return chartsMapper.getMonthTotalPriceByYear(year);
    }
}
