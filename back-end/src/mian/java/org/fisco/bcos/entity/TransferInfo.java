package org.fisco.bcos.entity;


/**
 * @author mazhixiu
 * @date 2021/3/14 14:35
 * @Email:861359297@qq.com
 */

import java.sql.Timestamp;
import java.util.Date;


//转账信息
public class TransferInfo {
    private int id;
    private String payer;
    private String payee;
    private String money;
    private String type;//类型：普通转账（支付订单费用，原材料费用，支付仓储费，委托费等），贷款转账。
    private Timestamp updateTime;
    private String remark;

    public TransferInfo() {
    }

    public TransferInfo(String payer, String payee, String money, String type, Timestamp updateTime, String remark) {
        this.payer = payer;
        this.payee = payee;
        this.money = money;
        this.type = type;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    public TransferInfo(String payer, String payee, String money, String type, Timestamp updateTime) {
        this.payer = payer;
        this.payee = payee;
        this.money = money;
        this.type = type;
        this.updateTime = updateTime;
    }

    public TransferInfo(String payer, String payee, String money, Timestamp updateTime) {
        this.payer = payer;
        this.payee = payee;
        this.money = money;
        this.updateTime = updateTime;
    }

    public TransferInfo(int id, String payer, String payee, String money, Timestamp updateTime) {
        this.id = id;
        this.payer = payer;
        this.payee = payee;
        this.money = money;
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TransferInfo{" +
                "id=" + id +
                ", payer='" + payer + '\'' +
                ", payee='" + payee + '\'' +
                ", money='" + money + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
