package org.fisco.bcos.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class User {
    private int ID;
    private String type;
    private String status;
    private String name;
    private String pwd;
    private String addr;

    public User() {
    }

    public User(int ID, String type, String status, String name, String pwd, String addr) {
        this.ID = ID;
        this.type = type;
        this.status = status;
        this.name = name;
        this.pwd = pwd;
        this.addr = addr;
    }

    public User(int ID, String type, String status, String name, String pwd) {
        this.ID = ID;
        this.type = type;
        this.status = status;
        this.name = name;
        this.pwd = pwd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
