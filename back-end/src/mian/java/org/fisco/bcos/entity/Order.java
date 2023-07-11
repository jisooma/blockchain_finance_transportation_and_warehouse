package org.fisco.bcos.entity;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/3/29 16:14
 * @Email:861359297@qq.com
 */
public class Order {
    private int id;
    private String uuid;
//    private String name;
//    private String specification;
//    private String number;
    private String status;
    private String buyer;
    private String supplier;
    private String totalMoney;
    private String remark;
    private Timestamp orderTime;

    public Order() {
    }

    public Order(String uuid, String status, String buyer, String supplier, String totalMoney, String remark, Timestamp orderTime) {
        this.uuid = uuid;
        this.status = status;
        this.buyer = buyer;
        this.supplier = supplier;
        this.totalMoney = totalMoney;
        this.remark = remark;
        this.orderTime = orderTime;
    }

    public Order(String uuid, String buyer, String supplier, String status, Timestamp orderTime) {
        this.uuid = uuid;
        this.buyer = buyer;
        this.supplier = supplier;
        this.status = status;
        this.orderTime = orderTime;
    }

    public Order(String uuid, String buyer, String supplier, Timestamp orderTime) {
        this.uuid = uuid;
        this.buyer = buyer;
        this.supplier = supplier;
        this.orderTime = orderTime;
    }

    public Order(int id, String uuid, String buyer, String supplier, String totalMoney, Timestamp orderTime) {
        this.id = id;
        this.uuid = uuid;
        this.buyer = buyer;
        this.supplier = supplier;
        this.totalMoney = totalMoney;
        this.orderTime = orderTime;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", buyer='" + buyer + '\'' +
                ", supplier='" + supplier + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
