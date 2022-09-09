package com.xiyuanli.mapper;

import com.xiyuanli.entity.Building;

import java.util.List;

public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    Building selectByPrimaryKey(Integer id);

    List<Building> selectAll();

    int updateByPrimaryKey(Building record);

    Integer rates();

    Integer rate(Integer id);
}