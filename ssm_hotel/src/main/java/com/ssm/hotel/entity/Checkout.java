package com.ssm.hotel.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zystart
 * @create 2023-06-07 15:55
 */
public class Checkout {
    private Integer id;
    private String checkoutnumber;
    private Integer checkinid;
    private BigDecimal consumeprice;
    private Date createdate;
    private Integer createdby;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckoutnumber() {
        return checkoutnumber;
    }

    public void setCheckoutnumber(String checkoutnumber) {
        this.checkoutnumber = checkoutnumber;
    }

    public Integer getCheckinid() {
        return checkinid;
    }

    public void setCheckinid(Integer checkinid) {
        this.checkinid = checkinid;
    }

    public BigDecimal getConsumeprice() {
        return consumeprice;
    }

    public void setConsumeprice(BigDecimal consumeprice) {
        this.consumeprice = consumeprice;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
