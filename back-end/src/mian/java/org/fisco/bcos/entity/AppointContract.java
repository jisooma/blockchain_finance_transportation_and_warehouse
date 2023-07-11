package org.fisco.bcos.entity;

/**
 * @author mazhixiu
 * @date 2021/2/1 14:17
 * @Email:861359297@qq.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


//委托合同
public class AppointContract {
    private  int id;
    private  String logistics;
    private  String bank;
    private  String money;
    private  String status;
    private Timestamp startTime;
    private Timestamp updateTime;
    private Timestamp endTime;

    public Timestamp getstartTime() {
        return startTime;
    }

    public void setstartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public AppointContract() {
    }

    public AppointContract(String logistics, String bank, String money,String status, Timestamp updateTime, Timestamp endTime) {
        this.logistics = logistics;
        this.bank = bank;
        this.money = money;
        this.status = status;
        this.updateTime = updateTime;
        this.endTime = endTime;
    }

    public AppointContract(int id, String logistics, String bank, String money, String status, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.logistics = logistics;
        this.bank = bank;
        this.money = money;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
