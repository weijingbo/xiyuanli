package com.xiyuanli.mapper;

import com.xiyuanli.entity.Wrong;

import java.sql.Date;
import java.util.List;

public interface WrongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wrong wrong);

    Wrong selectByPrimaryKey(Integer id);

    List<Wrong> selectAll();

    int updateByPrimaryKey(Wrong record);

    List<Wrong> selectByName(String uname);
}