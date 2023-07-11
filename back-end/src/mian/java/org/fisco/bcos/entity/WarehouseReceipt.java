package org.fisco.bcos.entity;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/2/21 16:24
 * @Email:861359297@qq.com
 */
//仓单
public class WarehouseReceipt {
    private int waNo;
    private String holder;
    private  String logistics;
    private int reno;//库存编号
    private  String address;//入库地点
    private String valMoney;//评估价值
    private  String status;//状态 在库，出库
    private Timestamp updateTime;
    private String remark;

    public WarehouseReceipt() {
    }

    public WarehouseReceipt(String holder, String logistics, int reno, String address, String valMoney, String status, Timestamp updateTime, String remark) {
        this.holder = holder;
        this.logistics = logistics;
        this.reno = reno;
        this.address = address;
        this.valMoney = valMoney;
        this.status = status;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    public WarehouseReceipt(String holder, String logistics, int reno, String address, String valMoney, String status, Timestamp updateTime) {
        this.holder = holder;
        this.logistics = logistics;
        this.reno = reno;
        this.address = address;
        this.valMoney = valMoney;
        this.status = status;
        this.updateTime = updateTime;
    }

    public WarehouseReceipt(int no, String holder, String logistics, int reno, String address, String valMoney, String status, Timestamp updateTime) {
        this.waNo = no;
        this.holder = holder;
        this.logistics = logistics;
        this.reno = reno;
        this.address = address;
        this.valMoney = valMoney;
        this.status = status;
        this.updateTime = updateTime;
    }

    public int getWaNo() {
        return waNo;
    }

    public void setWaNo(int no) {
        this.waNo = no;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getValMoney() {
        return valMoney;
    }

    public void setValMoney(String valMoney) {
        this.valMoney = valMoney;
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

    public int getReno() {
        return reno;
    }

    public void setReno(int reno) {
        this.reno = reno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WarehouseReceipt{" +
                "no=" + waNo +
                ", holder='" + holder + '\'' +
                ", logistics='" + logistics + '\'' +
                ", reno=" + reno +
                ", address='" + address + '\'' +
                ", valMoney='" + valMoney + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
