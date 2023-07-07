package com.ssm.hotel.entity;

/**
 * @author zystart
 * @create 2023-05-29 19:09
 */
public class Room {
    private Integer id;
    private String photo;
    private String roomnum;
    private Integer roomtypeid;
    private Integer floorid;
    private Integer status;
    private String roomrequirement;
    private String remark;
    private String roomdesc;

    private String typename;
    private String floorname;

    private String statusStr;

    private Double price;

    private Integer bednum;

    public Integer getBednum() {
        return bednum;
    }

    public void setBednum(Integer bednum) {
        this.bednum = bednum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatusStr() {
        //判断状态是否为空
        if(status!=null){
            switch (status){
                case 1:
                    statusStr = "已预定";
                    break;
                case 2:
                    statusStr = "已入住";
                    break;
                case 3:
                    statusStr = "可预订";
                    break;
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Integer roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public Integer getFloorid() {
        return floorid;
    }

    public void setFloorid(Integer floorid) {
        this.floorid = floorid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoomrequirement() {
        return roomrequirement;
    }

    public void setRoomrequirement(String roomrequirement) {
        this.roomrequirement = roomrequirement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoomdesc() {
        return roomdesc;
    }

    public void setRoomdesc(String roomdesc) {
        this.roomdesc = roomdesc;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getFloorname() {
        return floorname;
    }

    public void setFloorname(String floorname) {
        this.floorname = floorname;
    }
}
