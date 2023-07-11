package org.fisco.bcos.entity;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/3/25 15:17
 * @Email:861359297@qq.com
 */
//仓单抵押合同
public class LoanContract {
    int id;
    String enterprise;//贷款企业
    String bank;//银行
    String loanPurpose;//贷款目的
    String Money;//贷款金额
    int waNo;//质押仓单
    int reciptId;//贷款账单
    double interests;//利息
    String repayMoney;//应还金额
    Timestamp startTime;//签署日期
    Timestamp dueTime;//截止日期
    String status;


    public LoanContract(int id, String enterprise, String bank, String loanPurpose, String money, int waNo, double interests, String repayMoney, Timestamp startTime,Timestamp dueTime, String status) {
        this.id = id;
        this.enterprise = enterprise;
        this.bank = bank;
        this.loanPurpose = loanPurpose;
        this.Money = money;
        this.waNo = waNo;
        this.interests = interests;
        this.repayMoney = repayMoney;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.status = status;
    }

    public LoanContract(String enterprise, String bank, String loanPurpose, String money, int waNo, double interests, String repayMoney, Timestamp startTime, Timestamp dueTime, String status) {
        this.enterprise = enterprise;
        this.bank = bank;
        this.loanPurpose = loanPurpose;
        this.Money = money;
        this.waNo = waNo;
        this.interests = interests;
        this.repayMoney = repayMoney;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.status = status;
    }

    public LoanContract(String enterprise, String bank, String loanPurpose, String money, int waNo, int reciptId, double interests, String repayMoney, Timestamp startTime, Timestamp dueTime, String status) {
        this.enterprise = enterprise;
        this.bank = bank;
        this.loanPurpose = loanPurpose;
        Money = money;
        this.waNo = waNo;
        this.reciptId = reciptId;
        this.interests = interests;
        this.repayMoney = repayMoney;
        this.startTime = startTime;
        this.dueTime = dueTime;
        this.status = status;
    }

    public LoanContract() {
    }

    public int getReciptId() {
        return reciptId;
    }

    public void setReciptId(int reciptId) {
        this.reciptId = reciptId;
    }

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public int getWaNo() {
        return waNo;
    }

    public void setWaNo(int waNo) {
        this.waNo = waNo;
    }

    public double getInterests() {
        return interests;
    }

    public void setInterests(double interests) {
        this.interests = interests;
    }

    public String getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(String repayMoney) {
        this.repayMoney = repayMoney;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getDueTime() {
        return dueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        this.dueTime = dueTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoanContract{" +
                "id=" + id +
                ", enterprise='" + enterprise + '\'' +
                ", bank='" + bank + '\'' +
                ", loanPurpose='" + loanPurpose + '\'' +
                ", Money='" + Money + '\'' +
                ", waNo=" + waNo +
                ", reciptId=" + reciptId +
                ", interests=" + interests +
                ", repayMoney='" + repayMoney + '\'' +
                ", startTime=" + startTime +
                ", dueTime=" + dueTime +
                ", status='" + status + '\'' +
                '}';
    }
}
