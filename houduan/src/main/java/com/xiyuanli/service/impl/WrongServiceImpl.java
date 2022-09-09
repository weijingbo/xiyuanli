package com.xiyuanli.service.impl;

import com.xiyuanli.entity.Wrong;
import com.xiyuanli.mapper.WrongMapper;
import com.xiyuanli.service.WrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrongServiceImpl implements WrongService {

    @Autowired
    private WrongMapper wrongMapper;

    @Override
    public Integer insert(Wrong wrong) {
        return wrongMapper.insert(wrong);
    }

    @Override
    public List<Wrong> getWrongs() {
        return wrongMapper.selectAll();
    }

    @Override
    public Integer updateWrong(Wrong wrong) {
        return wrongMapper.updateByPrimaryKey(wrong);
    }

    @Override
    public Integer deleteWrong(Integer id) {
        return wrongMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Wrong> getWrongByName(String name) {
        return wrongMapper.selectByName(name);
    }
}
