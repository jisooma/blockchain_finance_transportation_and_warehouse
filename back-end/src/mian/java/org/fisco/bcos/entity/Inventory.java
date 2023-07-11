package org.fisco.bcos.entity;


/**
 * @author mazhixiu
 * @date 2021/2/1 14:39
 * @Email:861359297@qq.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

//仓储合同
public class Inventory {
    private  int id;
    private  String enterprise;
    private String logistics;
    private int waNo;//仓单编号
    private String reMoney;//仓储费
    private String status;
    private Timestamp updateTime;
    private Timestamp endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public int getWaNo() {
        return waNo;
    }

    public void setWaNo(int waNo) {
        this.waNo = waNo;
    }

    public String getReMoney() {
        return reMoney;
    }

    public void setReMoney(String reMoney) {
        this.reMoney = reMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Inventory() {
    }

    public Inventory(int id, String enterprise, String logistics, int reNo, String reMoney,String status, Timestamp updateTime, Timestamp endTime) {
        this.id = id;
        this.enterprise = enterprise;
        this.logistics = logistics;
        this.waNo = reNo;
        this.reMoney = reMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.endTime = endTime;
    }

    public Inventory(String enterprise, String logistics, int reNo, String reMoney, String status, Timestamp updateTime, Timestamp endTime) {
        this.enterprise = enterprise;
        this.logistics = logistics;
        this.waNo = reNo;
        this.reMoney = reMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", enterprise='" + enterprise + '\'' +
                ", logistics='" + logistics + '\'' +
                ", waNo=" + waNo +
                ", reMoney='" + reMoney + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                ", endTime=" + endTime +
                '}';
    }
}
