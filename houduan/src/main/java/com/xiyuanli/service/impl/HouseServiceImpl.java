package com.xiyuanli.service.impl;

import com.xiyuanli.entity.Building;
import com.xiyuanli.entity.House;
import com.xiyuanli.mapper.BuildingMapper;
import com.xiyuanli.mapper.HouseMapper;
import com.xiyuanli.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private HouseMapper houseMapper;


    @Override
    public List<Building> getBuildings() {
        return buildingMapper.selectAll();
    }

    @Override
    public List<House> getHouse(Integer belong) {
        return houseMapper.selectHouseByBelong(belong);
    }

    @Override
    public Integer insertHouse(House house) {
        return houseMapper.insert(house);
    }

    @Override
    public Integer updateHouse(House house) {
        return houseMapper.updateByPrimaryKey(house);
    }

    @Override
    public Integer deleteHouse(Integer id) {
        return houseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<House> getHouses() {
        return houseMapper.selectAll();
    }

    @Override
    public List<House> getHouseByName(String name) {
        return houseMapper.selectHouseByUname(name);
    }

    @Override
    public List<String> getNames() {
        return houseMapper.getNames();
    }

    @Override
    public Integer rates() {
        return buildingMapper.rates();
    }

    @Override
    public Integer rate(Integer id) {
        return buildingMapper.rate(id);
    }
}
