package com.xiyuanli.mapper;

import com.xiyuanli.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByName(String name);

    List<User> getUser(String name);

    List<User> selectByBid(Integer bid);
}