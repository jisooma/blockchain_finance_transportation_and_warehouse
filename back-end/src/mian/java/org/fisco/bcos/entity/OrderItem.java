package org.fisco.bcos.entity;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/3/29 21:17
 * @Email:861359297@qq.com
 */
public class OrderItem {
    private int orID;
    private String uuid;
    private String name;
    private String specification;
    private String number;
    private String status;
    private String remark;
    private Timestamp updateTime;

    public OrderItem() {
    }

    public OrderItem(String uuid, String name, String specification, String number, String status, String remark, Timestamp updateTime) {
        this.uuid = uuid;
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.status = status;
        this.remark = remark;
        this.updateTime = updateTime;
    }

    public OrderItem(int orID, String uuid, String name, String specification, String number, String status, String remark, Timestamp updateTime) {
        this.orID = orID;
        this.uuid = uuid;
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.status = status;
        this.remark = remark;
        this.updateTime = updateTime;
    }

    public int getOrID() {
        return orID;
    }

    public void setOrID(int orID) {
        this.orID = orID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orID=" + orID +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
