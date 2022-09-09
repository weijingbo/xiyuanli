package com.xiyuanli.controller;


import com.xiyuanli.entity.Building;
import com.xiyuanli.entity.House;
import com.xiyuanli.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 92171
 */
@CrossOrigin
@ResponseBody
@Controller
public class HouseController {

    @Autowired
    public HouseService houseService;

    @RequestMapping("/getBuildings")
    public List<Building> GetBuidings() {
        return houseService.getBuildings();
    }

    @RequestMapping("/getHouses")
    public List<House> GetHouses() {
        return houseService.getHouses();
    }

    @RequestMapping("/insertHouse")
    public Integer InsertHouse(@RequestBody House house) {
        return houseService.insertHouse(house);
    }

    @RequestMapping("/updateHouse")
    public Integer UpdateBuidings(@RequestBody House house) {
        return houseService.updateHouse(house);
    }

    @RequestMapping("/deleteHouse")
    public Integer DeleteBuidings(@RequestParam("id") Integer id) {
        return houseService.deleteHouse(id);
    }

    @RequestMapping("/getHouse")
    public List<House> GetHouses(@RequestParam("belong") Integer belong) {
        return houseService.getHouse(belong);
    }

    @RequestMapping("/getHouseByName")
    public List<House> GetHouseByName(@RequestParam("name") String name) {
        return houseService.getHouseByName(name);
    }

    @RequestMapping("/getUserName")
    public List<String> GetName() {
        return houseService.getNames();
    }

    @RequestMapping("/rates")
    public Integer Rates() {
        return houseService.rates();
    }

    @RequestMapping("/rate")
    public Integer Rate(@RequestParam("bid") Integer id) {
        return houseService.rate(id);
    }

}
