package com.ssm.hotel.vo;

import com.ssm.hotel.entity.Orders;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用于分页
 * @author zystart
 * @create 2022-11-18 22:35
 */
public class OrdersVo extends Orders {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
