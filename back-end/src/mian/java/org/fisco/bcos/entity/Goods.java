package org.fisco.bcos.entity;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/3/25 11:18
 * @Email:861359297@qq.com
 */
//货物信息
public class Goods {
    private int goNo;//编号
    private String name;//名字
    private String specification;//规格
    private int number;//数量
    private String addr;//产地
    private String holder;//所属企业
    private Timestamp InputTime;//录入时间
    private String status;//状态：未抵押，已抵押
    private String remark;//备注


    @Override
    public String toString() {
        return "Goods{" +
                "goNo=" + goNo +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", number=" + number +
                ", addr='" + addr + '\'' +
                ", holder='" + holder + '\'' +
                ", InputTime=" + InputTime +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Timestamp getInputTime() {
        return InputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        InputTime = inputTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Goods(String name, String specification, int number, String addr, String holder, Timestamp inputTime, String status, String remark) {
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.addr = addr;
        this.holder = holder;
        InputTime = inputTime;
        this.status = status;
        this.remark = remark;
    }

    public Goods(int goNo, String name, String specification, int number, String addr, String holder, Timestamp inputTime, String status, String remark) {
        this.goNo = goNo;
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.addr = addr;
        this.holder = holder;
        InputTime = inputTime;
        this.status = status;
        this.remark = remark;
    }
}
