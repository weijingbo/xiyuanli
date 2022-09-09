package com.xiyuanli.service;


import com.xiyuanli.entity.Property;

import java.util.List;


public interface PropertyService {

    public List<Property> getProperyByUid(Integer uid);

    public Integer PayProperty(Integer id, String state);

    public Integer Insert(Property property);

    public Integer Delete(Integer id);

    public List<Property> GetByName(String name);

    public List<Property> GetByYear(Integer year);

    public List<Property> nopay();

    public void urge();
}
