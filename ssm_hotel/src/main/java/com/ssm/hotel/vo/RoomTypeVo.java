package com.ssm.hotel.vo;

import com.ssm.hotel.entity.RoomType;

/**
 * 用于分页
 * @author zystart
 * @create 2022-11-18 22:35
 */
public class RoomTypeVo extends RoomType {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

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
