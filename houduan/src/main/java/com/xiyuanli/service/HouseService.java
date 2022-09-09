package com.xiyuanli.service;


import com.xiyuanli.entity.Building;
import com.xiyuanli.entity.House;

import java.util.List;

public interface HouseService {

    public List<Building> getBuildings();

    public List<House> getHouse(Integer belong);

    public Integer insertHouse(House house);

    public Integer updateHouse(House house);

    public Integer deleteHouse(Integer id);

    public List<House> getHouseByName(String name);

    public List<House> getHouses();

    public List<String> getNames();

    public Integer rates();

    public Integer rate(Integer id);
}
