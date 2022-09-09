package com.xiyuanli.service.impl;

import com.xiyuanli.entity.Patrol;
import com.xiyuanli.mapper.PatrolMapper;
import com.xiyuanli.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PatrolServiceImpl implements PatrolService {

    @Autowired
    private PatrolMapper patrolMapper;

    @Override
    public Integer insert(Patrol patrol) {
        return patrolMapper.insert(patrol);
    }

    @Override
    public Integer update(Patrol patrol) {
        return patrolMapper.updateByPrimaryKey(patrol);
    }

    @Override
    public Integer delete(Integer id) {
        return patrolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Patrol> selectAll() {
        return patrolMapper.selectAll();
    }

    @Override
    public List<Patrol> selectByTime(Date date1, Date date2) {
        return patrolMapper.selectByTime(date1, date2);
    }
}
