package org.fisco.bcos.entity;

/**
 * @author mazhixiu
 * @date 2021/2/14 14:35
 * @Email:861359297@qq.com
 */



import java.sql.Timestamp;

//货物入库信息
public class Respository {
    private int reNo;//编号
    private String name;//名字
    private String specification;//规格
    private  int number;//数量
    private String addr;//产地
    private  String holder;//申请企业
    private  String logistics;//申请企业
    private Timestamp updateTime;//申请时间
    private String status;//申请状态
    private  String remark;//备注

    public Respository() {
    }

    public Respository(String name, String specification, int number, String addr, String holder, String logistics, Timestamp updateTime, String status, String remark) {
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.addr = addr;
        this.holder = holder;
        this.logistics = logistics;
        this.updateTime = updateTime;
        this.status = status;
        this.remark = remark;
    }

    public Respository(String name, String specification, int number, String addr, String holder, Timestamp updateTime, String status, String remark) {
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.addr = addr;
        this.holder = holder;
        this.updateTime = updateTime;
        this.status = status;
        this.remark = remark;
    }

    public Respository(int no, String name, String specification, int number, String addr, String holder, String logistics, Timestamp updateTime, String status, String remark) {
        this.reNo = no;
        this.name = name;
        this.specification = specification;
        this.number = number;
        this.addr = addr;
        this.holder = holder;
        this.logistics = logistics;
        this.updateTime = updateTime;
        this.status = status;
        this.remark = remark;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReNo() {
        return reNo;
    }

    public void setReNo(int no) {
        this.reNo = no;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
