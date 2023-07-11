package org.fisco.bcos.chaindata;

import java.sql.Timestamp;

/**
 * @author mazhixiu
 * @date 2021/4/7 16:20
 * @Email:861359297@qq.com
 */
public class ContractHashDetail {
    int id;
    String blockNumber;
    String thHash;
    String fromAddr;
    String toAddr;
    Timestamp time;
    int number;//合同hash存证的链上number

    public ContractHashDetail() {
    }

    public ContractHashDetail(String blockNumber, String thHash, String fromAddr, String toAddr, Timestamp time, int number) {
        this.blockNumber = blockNumber;
        this.thHash = thHash;
        this.fromAddr = fromAddr;
        this.toAddr = toAddr;
        this.time = time;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getThHash() {
        return thHash;
    }

    public void setThHash(String thHash) {
        this.thHash = thHash;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ContractHashDetail{" +
                "id=" + id +
                ", blockNumber='" + blockNumber + '\'' +
                ", thHash='" + thHash + '\'' +
                ", fromAddr='" + fromAddr + '\'' +
                ", toAddr='" + toAddr + '\'' +
                ", time='" + time + '\'' +
                ", number=" + number +
                '}';
    }
}
