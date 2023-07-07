package com.ssm.hotel.vo;

import com.ssm.hotel.entity.Room;

/**
 * 用于分页
 * @author zystart
 * @create 2022-11-18 22:35
 */
public class RoomVo extends Room {
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
