package com.xiyuanli.controller;


import com.xiyuanli.entity.Patrol;
import com.xiyuanli.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 92171
 */
@Controller
@CrossOrigin
@ResponseBody
public class PatrolController {

    @Autowired
    PatrolService patrolService;

    @RequestMapping("/getPatrols")
    public List<Patrol> GetPartorls() {
        return patrolService.selectAll();
    }

    @RequestMapping("/insertPatrol")
    public Integer InsertPatrol(@RequestBody Patrol patrol) {
        return patrolService.insert(patrol);
    }

    @RequestMapping("/updatePatrol")
    public Integer UpdatePatrol(@RequestBody Patrol patrol) {
        return patrolService.update(patrol);
    }

    @RequestMapping("/deletePatrol")
    public Integer DeletePatrol(@RequestParam("id") Integer id) {
        return patrolService.delete(id);
    }

    @RequestMapping("/getPatrol")
    public List<Patrol> GetPatrol(@RequestBody List<Date> times) {
        Date stime = times.get(0);
        Date etime = times.get(1);
        return patrolService.selectByTime(stime, etime);
    }
}
