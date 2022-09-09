package com.xiyuanli.entity;

public class Water {
    private Integer id;

    private String date;

    private Integer uid;

    private String uname;

    private Float number;

    private Float price;

    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Water{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", flag='" + flag + '\'' +
                '}';
    }
}