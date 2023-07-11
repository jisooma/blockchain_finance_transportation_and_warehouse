package org.fisco.bcos.entity;

/**
 * @author mazhixiu
 * @date 2021/2/1 15:34
 * @Email:861359297@qq.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;


//贷款申请
public class LoanApply {
    private int ID;
    private String enterprise;//贷款企业
    private String bank;//贷款银行
    private int coID;//担保合同id
    private int inID;//库存合同id
    private String loanPurpose;//贷款目的
    private String loMoney;//贷款金额
    private String status;//状态：0申请,1同意,2拒绝
    private Timestamp applyTime;//申请时间
    private Timestamp updateTime;//更新时间
    private Timestamp dueTime;//承诺归还日期

    public LoanApply() {
    }
    public LoanApply(String enterprise, String bank, int coID, int inID, String loanPurpose, String status, Timestamp applyTime, Timestamp dueTime) {
        this.enterprise = enterprise;
        this.bank = bank;
        this.coID = coID;
        this.inID = inID;
        this.loanPurpose = loanPurpose;
        this.status = status;
        this.applyTime = applyTime;
        this.dueTime = dueTime;
    }

    public LoanApply(String enterprise, String bank, int coID, int inID, String loanPurpose, String loMoney, String status, Timestamp updateTime, Timestamp dueTime) {
        this.enterprise = enterprise;
        this.bank = bank;
        this.coID = coID;
        this.inID = inID;
        this.loanPurpose = loanPurpose;
        this.loMoney = loMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.dueTime = dueTime;
    }

    public LoanApply(int ID, String enterprise, String bank, int coID, int inID, String loanPurpose, String loMoney, String status, Timestamp updateTime, Timestamp dueTime) {
        this.ID = ID;
        this.enterprise = enterprise;
        this.bank = bank;
        this.coID = coID;
        this.inID = inID;
        this.loanPurpose = loanPurpose;
        this.loMoney = loMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.dueTime = dueTime;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
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

    public int getCoID() {
        return coID;
    }

    public void setCoID(int coID) {
        this.coID = coID;
    }

    public int getInID() {
        return inID;
    }

    public void setInID(int inID) {
        this.inID = inID;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getLoMoney() {
        return loMoney;
    }

    public void setLoMoney(String loMoney) {
        this.loMoney = loMoney;
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

    public Timestamp getDueTime() {
        return dueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        this.dueTime = dueTime;
    }
}
