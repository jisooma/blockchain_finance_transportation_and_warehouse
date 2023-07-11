package org.fisco.bcos.entity;

/**
 * @author mazhixiu
 * @date 2021/2/1 15:35
 * @Email:861359297@qq.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Data
@ToString
//贷款账单
public class LoanRecipt {
    private int ID;
    private String enterprise;
    private String bank;
    private int loanID;
    private String allMoney;
    private String reMoney;
    private String status;
    private Timestamp updateTime;
    private Timestamp dueTime;

    public LoanRecipt() {
    }

    public LoanRecipt(String enterprise, String bank, int loanID, String allMoney, String reMoney, String status, Timestamp updateTime, Timestamp dueTime) {
        this.enterprise = enterprise;
        this.bank = bank;
        this.loanID = loanID;
        this.allMoney = allMoney;
        this.reMoney = reMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.dueTime = dueTime;
    }

    public LoanRecipt(int ID, String enterprise, String bank, int loanID, String allMoney, String reMoney, String status, Timestamp updateTime,Timestamp dueTime) {
        this.ID = ID;
        this.enterprise = enterprise;
        this.bank = bank;
        this.loanID = loanID;
        this.allMoney = allMoney;
        this.reMoney = reMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.dueTime = dueTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        this.dueTime = dueTime;
    }
}
