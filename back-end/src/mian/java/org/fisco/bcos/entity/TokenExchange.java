package org.fisco.bcos.entity;

/**
 * @author mazhixiu
 * @date 2021/3/14 14:35
 * @Email:861359297@qq.com
 */

import java.sql.Timestamp;

public class TokenExchange {
    private int id;//兑付编号
    private String bank;//兑付银行
    private String initiator;//申请兑付的企业
    private String token;//申请兑付的数字货币金额
    private String status;//申请兑付状态
    private Timestamp updateTime;//兑付更新时间


    public TokenExchange() {
    }

    public TokenExchange(String bank, String initiator, String token, String status, Timestamp updateTime) {
        this.bank = bank;
        this.initiator = initiator;
        this.token = token;
        this.status = status;
        this.updateTime = updateTime;
    }

    public TokenExchange(int id, String bank, String initiator, String token, String status, Timestamp updateTime) {
        this.id = id;
        this.bank = bank;
        this.initiator = initiator;
        this.token = token;
        this.status = status;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getinitiator() {
        return initiator;
    }

    public void setinitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public String toString() {
        return "TokenExchange{" +
                "id=" + id +
                ", bank='" + bank + '\'' +
                ", initiator='" + initiator + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                '}';
    }
}
