package com.xiyuanli.service.impl;

import com.xiyuanli.entity.Parking;
import com.xiyuanli.mapper.ParkingMapper;
import com.xiyuanli.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public List<Parking> getParkings() {
        return parkingMapper.selectAll();
    }

    @Override
    public Integer updateParking(Parking parking) {
        return parkingMapper.updateByPrimaryKey(parking);
    }


    @Override
    public Integer deleteParking(Integer id) {
        return parkingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Parking> getParking(String name) {
        return parkingMapper.selectByUname(name);
    }

    @Override
    public Integer insertParking(Parking parking) {
        return parkingMapper.insert(parking);
    }
}
