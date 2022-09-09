package com.xiyuanli.controller;


import com.xiyuanli.entity.Property;
import com.xiyuanli.service.ExcelService;
import com.xiyuanli.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ResponseBody
@CrossOrigin
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private ExcelService excelService;


    @RequestMapping("/getProperty")
    public List<Property> GetPropertyByUid(@RequestParam("uid") Integer uid) {
        List<Property> properties = propertyService.getProperyByUid(uid);
        return properties;
    }

    @RequestMapping("/payProperty")
    public Integer PayProperty(@RequestParam("id") Integer id) {
        String state = "已缴费";
        return propertyService.PayProperty(id, state);
    }

    @RequestMapping("/insertProperties")
    public Integer InsertProperties(@RequestBody List<Property> properties) {
        Integer flag = 0;
        for (Property property : properties) {
            flag = propertyService.Insert(property);
        }
        return flag;
    }

    @RequestMapping("/deleteProperties")
    public Integer DeleteProperties(@RequestBody List<Property> properties) {
        Integer flag = 0;
        for (Property property : properties) {
            flag = propertyService.Delete(property.getId());
        }
        return flag;
    }

    @RequestMapping("/deleteProperty")
    public Integer DeleteProperty(@RequestParam("id") Integer id) {
        return propertyService.Delete(id);
    }

    @RequestMapping("/getByName")
    public List<Property> GetByName(@RequestParam("name") String name) {
        return propertyService.GetByName(name);
    }

    @RequestMapping("/getByYear")
    public List<Property> GetByYear(@RequestParam("year") Integer year) {
        return propertyService.GetByYear(year);
    }


    @RequestMapping("/getPropertyExcel")
    public void GetPropertyExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        excelService.createPropertySheel(request, response);
    }

    @RequestMapping("/PNoPay")
    public List<Property> PNoPay() {
        return propertyService.nopay();
    }

    @RequestMapping("/message")
    public void Message() {
        propertyService.urge();
    }
}
