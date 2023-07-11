package org.fisco.bcos.entity;


/**
 * @author mazhixiu
 * @date 2021/3/14 14:35
 * @Email:861359297@qq.com
 */

import java.sql.Timestamp;


//价值评估报告
public class ValueReport {
    private  int id;
    private String logistics;
    private String enterprise;
    private int reNo;
    private String valMoney;
    private int status;
    private Timestamp updateTime;

    public ValueReport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public int getReNo() {
        return reNo;
    }

    public void setReNo(int reNo) {
        this.reNo = reNo;
    }

    public String getValMoney() {
        return valMoney;
    }

    public void setValMoney(String valMoney) {
        this.valMoney = valMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public ValueReport(String logistics, String enterprise, int reNo, String valMoney, int status, Timestamp updateTime) {
        this.logistics = logistics;
        this.enterprise = enterprise;
        this.reNo = reNo;
        this.valMoney = valMoney;
        this.status = status;
        this.updateTime = updateTime;
    }

    public ValueReport(int id, String logistics, String enterprise, int reNo, String valMoney, int status, Timestamp updateTime) {
        this.id = id;
        this.logistics = logistics;
        this.enterprise = enterprise;
        this.reNo = reNo;
        this.valMoney = valMoney;
        this.status = status;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ValueReport{" +
                "id=" + id +
                ", logistics='" + logistics + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", reNo=" + reNo +
                ", valMoney='" + valMoney + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                '}';
    }
}
