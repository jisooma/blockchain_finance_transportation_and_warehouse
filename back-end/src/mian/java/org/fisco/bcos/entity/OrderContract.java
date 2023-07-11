package org.fisco.bcos.entity;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/3/30 9:53
 * @Email:861359297@qq.com
 */
public class OrderContract {
    private int id;
    private String supplier;
    private String buyer;
    private String uuid;//订单编号
    private String demands;//需求
    private String addr;//交货地点
    private Timestamp time;//交货时间
    private String expiration;//保质期
    private Timestamp startTime;//签署时间
    private Timestamp endTime;//截止时间
    private String status;//状态
    private String remark;//备注

    public OrderContract() {
    }

    public OrderContract(String supplier, String buyer, String uuid, String demands, String addr, Timestamp time, String expiration, Timestamp startTime, Timestamp endTime, String status, String remark) {
        this.supplier = supplier;
        this.buyer = buyer;
        this.uuid = uuid;
        this.demands = demands;
        this.addr = addr;
        this.time = time;
        this.expiration = expiration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.remark = remark;
    }

    public OrderContract(String uuid, String demands, String addr, Timestamp time, String expiration, Timestamp startTime, Timestamp endTime, String status, String remark) {
        this.uuid = uuid;
        this.demands = demands;
        this.addr = addr;
        this.time = time;
        this.expiration = expiration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.remark = remark;
    }

    public OrderContract(String uuid, String demands, String addr, Timestamp time, String expiration, Timestamp startTime, Timestamp endTime, String remark) {
        this.uuid = uuid;
        this.demands = demands;
        this.addr = addr;
        this.time = time;
        this.expiration = expiration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
    }

    public OrderContract(int id,String uuid, String demands, String addr, Timestamp time, String expiration, Timestamp startTime, Timestamp endTime, String remark) {
        this.id = id;
        this.uuid = uuid;
        this.demands = demands;
        this.addr = addr;
        this.time = time;
        this.expiration = expiration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuuid() {
        return uuid;
    }

    public void setuuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDemands() {
        return demands;
    }

    public void setDemands(String demands) {
        this.demands = demands;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OrderContract{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", demands='" + demands + '\'' +
                ", addr='" + addr + '\'' +
                ", time=" + time +
                ", expiration=" + expiration +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
