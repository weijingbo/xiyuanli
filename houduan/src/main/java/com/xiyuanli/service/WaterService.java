package com.xiyuanli.service;


import com.xiyuanli.entity.Water;

import java.util.List;


public interface WaterService {

    public List<Water> getWater(Integer uid);

    public Integer PayWater(Integer id, String flag);

    public Integer insert(Water water);

    public List<Water> selectWaters(String date);

    public Integer delete(Integer id);

    public List<Water> getWaterByName(String name);

    public List<Water> getNoPay();

    public void urge();
}
