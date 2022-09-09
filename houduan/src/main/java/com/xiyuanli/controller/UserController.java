package com.xiyuanli.controller;


import com.xiyuanli.entity.User;
import com.xiyuanli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public HashMap Login(@RequestParam("name") String name, @RequestParam("password") String password,
                         HttpServletRequest request) {
        HashMap hashMap = new HashMap<>();
        User user = userService.Login(name);
        boolean isuser = false;

        if (user != null) {
            if (user.getPassword().equals(password)) {
                isuser = true;
            }
        }
        hashMap.put("isuser", isuser);
        hashMap.put("user", user);
        return hashMap;
    }


    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }


    @RequestMapping("/updateUser")
    public Integer updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping("/insertUser")
    public Integer InsertUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping("/deleteUser")
    public Integer DeleteUser(@RequestParam("id") Integer id) {
        return userService.delete(id);
    }

    @RequestMapping("/getUser")
    public List<User> GetUser(@RequestParam("name") String name) {
        return userService.getUser(name);
    }

    @RequestMapping("/getUserByBid")
    public List<User> selectByBid(@RequestParam("bid") Integer bid) {
        return userService.selectByBid(bid);
    }
}
