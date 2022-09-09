package com.xiyuanli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Wrong {
    private Integer id;

    private String uname;

    private String bname;

    private Integer number;

    private String detail;

    private String ename;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date sdate;

    private Integer state;

    private String evaluate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "Wrong{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", bname='" + bname + '\'' +
                ", number=" + number +
                ", detail='" + detail + '\'' +
                ", ename='" + ename + '\'' +
                ", sdate=" + sdate +
                ", state=" + state +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}