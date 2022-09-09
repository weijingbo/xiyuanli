package com.xiyuanli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Patrol {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    private String ename1;

    private String ename2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEname1() {
        return ename1;
    }

    public void setEname1(String ename1) {
        this.ename1 = ename1;
    }

    public String getEname2() {
        return ename2;
    }

    public void setEname2(String ename2) {
        this.ename2 = ename2;
    }

    @Override
    public String toString() {
        return "Patrol{" +
                "id=" + id +
                ", date=" + date +
                ", ename1='" + ename1 + '\'' +
                ", ename2='" + ename2 + '\'' +
                '}';
    }
}