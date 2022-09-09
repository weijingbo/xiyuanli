package com.xiyuanli.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiyuanli.entity.Parking;
import com.xiyuanli.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @RequestMapping("/getParkings")
    public List<Parking> getParkings() {
        List<Parking> parkings = parkingService.getParkings();
        return parkings;
    }

    @RequestMapping("/updateParking")
    public Integer updateParking(@RequestBody Parking parking) {
        return parkingService.updateParking(parking);
    }

    @RequestMapping("/getParking")
    public List<Parking> getParking(@RequestParam("uname") String uname) {
        return parkingService.getParking(uname);
    }

    @RequestMapping("/insertParking")
    public Integer insertParking(@RequestBody Parking parking) {
        return parkingService.insertParking(parking);
    }

    @RequestMapping("/deleteParking")
    public Integer deleteParking(@RequestParam("id") Integer id) {
        return parkingService.deleteParking(id);
    }
}
