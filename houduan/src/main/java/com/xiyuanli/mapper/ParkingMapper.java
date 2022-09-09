package com.xiyuanli.mapper;

import com.xiyuanli.entity.Parking;

import java.util.List;

public interface ParkingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Parking record);

    Parking selectByPrimaryKey(Integer id);

    List<Parking> selectAll();

    int updateByPrimaryKey(Parking record);

    List<Parking> selectByUname(String name);
}