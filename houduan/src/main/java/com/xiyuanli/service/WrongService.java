package com.xiyuanli.service;


import com.xiyuanli.entity.Wrong;

import java.util.List;

public interface WrongService {

    public Integer insert(Wrong wrong);

    public List<Wrong> getWrongs();

    public Integer updateWrong(Wrong wrong);

    public Integer deleteWrong(Integer id);

    public List<Wrong> getWrongByName(String name);
}
