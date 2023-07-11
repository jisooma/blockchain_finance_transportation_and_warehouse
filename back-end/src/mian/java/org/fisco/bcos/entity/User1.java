package org.fisco.bcos.entity;

//账户信息
public class User1 {
    private int id;
    private int type;
    private String name;
    private String password;
    private String bal;
    private int status;
    private String addr;
    private String privateKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public User1(int ID, int type, String name, String password, String bal, int status, String addr, String privateKey) {
        this.id = ID;
        this.type = type;
        this.name = name;
        this.password = password;
        this.bal = bal;
        this.status = status;
        this.addr = addr;
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "User1{" +
                "ID=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", bal='" + bal + '\'' +
                ", status=" + status +
                ", addr='" + addr + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }

    public User1(int type, String name, String password, String bal, int status, String addr, String privateKey) {
        this.type = type;
        this.name = name;
        this.password = password;
        this.bal = bal;
        this.status = status;
        this.addr = addr;
        this.privateKey = privateKey;
    }
}
