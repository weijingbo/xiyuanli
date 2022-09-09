package com.xiyuanli.mapper;

import com.xiyuanli.entity.VO.WaterMessage;
import com.xiyuanli.entity.VO.WaterShell;
import com.xiyuanli.entity.Water;

import java.util.List;

public interface WaterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Water record);

    Water selectByPrimaryKey(Integer id);

    List<Water> selectAll();

    int updateByPrimaryKey(Water record);

    List<Water> getWaters(Integer uid);

    int PayWater(Integer id, String flag);

    List<Water> selectByDate(String date);

    List<Water> getWaterByName(String name);

    List<WaterShell> waterShell();

    List<Water> nopay();

    List<String> check();

    List<WaterMessage> urge();
}