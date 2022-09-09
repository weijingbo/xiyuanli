package com.xiyuanli.mapper;

import com.xiyuanli.entity.Property;
import com.xiyuanli.entity.VO.PropertyMessage;

import java.util.List;

public interface PropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Property record);

    Property selectByPrimaryKey(Integer id);

    List<Property> selectAll();

    int updateByPrimaryKey(Property record);

    List<Property> selectByUid(Integer id);

    Integer updateById(Integer id, String state);

    List<Property> getByName(String name);

    List<Property> getByYear(Integer year);

    List<PropertyMessage> urge();

    List<Property> nopay();
}