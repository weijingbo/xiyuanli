package com.xiyuanli.mapper;

import com.xiyuanli.entity.Patrol;

import java.util.Date;
import java.util.List;

public interface PatrolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Patrol record);

    Patrol selectByPrimaryKey(Integer id);

    List<Patrol> selectAll();

    int updateByPrimaryKey(Patrol record);

    List<Patrol> selectByTime(Date date1, Date date2);
}