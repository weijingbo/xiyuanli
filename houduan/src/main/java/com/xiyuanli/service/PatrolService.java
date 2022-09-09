package com.xiyuanli.service;

import com.xiyuanli.entity.Patrol;

import java.util.Date;
import java.util.List;

public interface PatrolService {

    public Integer insert(Patrol patrol);

    public Integer update(Patrol patrol);

    public Integer delete(Integer id);

    public List<Patrol> selectAll();

    public List<Patrol> selectByTime(Date date1, Date date2);
}
