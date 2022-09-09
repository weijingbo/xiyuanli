package com.xiyuanli.service.impl;

import com.xiyuanli.entity.User;
import com.xiyuanli.mapper.UserMapper;
import com.xiyuanli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User Login(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }


    @Override
    public Integer update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> getUser(String name) {
        return userMapper.getUser(name);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> selectByBid(Integer bid) {
        return userMapper.selectByBid(bid);
    }
}
