package com.ssm.hotel.vo;

import com.ssm.hotel.entity.Role;

/**
 * @author zystart
 * @create 2022-11-21 15:43
 */
public class RoleVo extends Role {
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
