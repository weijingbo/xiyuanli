package com.xiyuanli.mapper;

import com.xiyuanli.entity.House;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    House selectByPrimaryKey(Integer id);

    List<House> selectAll();

    int updateByPrimaryKey(House record);

    List<House> selectHouseByBelong(Integer belong);

    List<House> selectHouseByUname(String name);

    List<String> getNames();

}