package com.xiyuanli.controller;

import com.xiyuanli.entity.Water;
import com.xiyuanli.service.ExcelService;
import com.xiyuanli.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@CrossOrigin
@ResponseBody
public class WaterController {

    @Autowired
    private WaterService waterService;
    @Autowired
    private ExcelService excelService;


    @RequestMapping("/getwaters")
    private List<Water> getWater(@RequestParam("uid") Integer uid) {
        List<Water> waters = waterService.getWater(uid);
        return waters;
    }


    @RequestMapping("/paywater")
    private Integer PayWater(@RequestParam("id") Integer id) {
        String flag = "已缴费";
        Integer change = waterService.PayWater(id, flag);
        return change;
    }

    @RequestMapping("/insertWaters")
    private Integer InsertWaters(@RequestBody List<Water> waters) {
        Integer flag = 0;
        for (Water water : waters) {
            flag = waterService.insert(water);
        }
        return flag;
    }

    @RequestMapping("/selectWaters")
    private List<Water> SelectWaters(@RequestParam("date") String date) {
        return waterService.selectWaters(date);
    }

    @RequestMapping("/deleteWater")
    private Integer DeleteWater(@RequestParam("id") Integer id) {
        return waterService.delete(id);
    }

    @RequestMapping("/deleteWaters")
    private Integer DeleteWaters(@RequestBody List<Water> waters) {
        Integer flag = 0;
        for (Water water : waters) {
            waterService.delete(water.getId());
            flag = 1;
        }
        return flag;
    }

    @RequestMapping("/getWaterByName")
    private List<Water> GetWaterByName(@RequestParam("name") String name) {
        return waterService.getWaterByName(name);
    }

    @RequestMapping("/getWaterExcel")
    public void GetWaterExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        excelService.createWaterSheel(request, response);
    }

    @RequestMapping("/getNoPay")
    public List<Water> NoPay() {
        return waterService.getNoPay();
    }

    @RequestMapping("/urgeMessage")
    public void sendMessage() {
        waterService.urge();
    }
}
