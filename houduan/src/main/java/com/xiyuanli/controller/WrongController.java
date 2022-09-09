package com.xiyuanli.controller;

import com.xiyuanli.entity.Wrong;
import com.xiyuanli.service.WrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@CrossOrigin
@ResponseBody
public class WrongController {

    @Autowired
    private WrongService wrongService;

    @RequestMapping("/insertWrong")
    public Integer insertWrong(@RequestBody Wrong wrong) {
        Date date = new Date();
        wrong.setSdate(date);
        Integer state = 0;
        wrong.setState(state);
        return wrongService.insert(wrong);
    }

    @RequestMapping("/getWrong")
    public List<Wrong> GetWrongs() {
        return wrongService.getWrongs();
    }

    @RequestMapping("/updateWrong")
    public Integer UpdateWrong(@RequestBody Wrong wrong) {
        return wrongService.updateWrong(wrong);
    }

    @RequestMapping("/deleteWrong")
    public Integer UpdateWrong(@RequestParam("id") Integer id) {
        return wrongService.deleteWrong(id);
    }

    @RequestMapping("/getWrongByName")
    public List<Wrong> GetWrongByName(@RequestParam("name") String name) {
        return wrongService.getWrongByName(name);
    }
}
