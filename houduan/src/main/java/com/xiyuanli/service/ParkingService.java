package com.xiyuanli.service;


import com.xiyuanli.entity.Parking;

import java.util.List;

public interface ParkingService {

    public List<Parking> getParkings();

    public Integer updateParking(Parking parking);

    public Integer deleteParking(Integer id);

    public List<Parking> getParking(String name);

    public Integer insertParking(Parking parking);
}
