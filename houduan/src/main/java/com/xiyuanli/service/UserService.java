package com.xiyuanli.service;

import com.xiyuanli.entity.User;

import java.util.List;


public interface UserService {
    public User Login(String name);

    public List<User> getUsers();

    public Integer update(User user);

    public List<User> getUser(String name);

    public Integer insert(User user);

    public Integer delete(Integer id);

    public List<User> selectByBid(Integer bid);

}
