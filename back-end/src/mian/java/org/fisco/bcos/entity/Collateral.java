package org.fisco.bcos.entity;

/**
 * @author mazhixiu
 * @date 2021/2/1 14:23
 * @Email:861359297@qq.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;


//担保合同
public class Collateral{
    private int id;
    private String enterprise;
    private String consigner;
    private  String status;
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

    public String getConsigner() {
        return consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
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

    public Collateral(String enterprise, String consigner,String status, Timestamp updateTime, Timestamp endTime) {
        this.enterprise = enterprise;
        this.consigner = consigner;
        this.status = status;
        this.updateTime = updateTime;
        this.endTime = endTime;
    }

    public Collateral(int id, String enterprise, String consigner, String status, Timestamp updateTime, Timestamp endTime) {
        this.id = id;
        this.enterprise = enterprise;
        this.consigner = consigner;
        this.status = status;
        this.updateTime = updateTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Collateral{" +
                "id=" + id +
                ", enterprise='" + enterprise + '\'' +
                ", consigner='" + consigner + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", endTime=" + endTime +
                '}';
    }
}
