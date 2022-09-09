package com.xiyuanli.service.impl;

import com.xiyuanli.entity.Property;
import com.xiyuanli.entity.VO.PropertyMessage;
import com.xiyuanli.entity.VO.WaterMessage;
import com.xiyuanli.mapper.PropertyMapper;
import com.xiyuanli.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> getProperyByUid(Integer uid) {
        return propertyMapper.selectByUid(uid);
    }

    @Override
    public Integer PayProperty(Integer id, String state) {
        return propertyMapper.updateById(id, state);
    }

    @Override
    public Integer Insert(Property property) {
        return propertyMapper.insert(property);
    }

    @Override
    public Integer Delete(Integer id) {
        return propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Property> GetByName(String name) {
        return propertyMapper.getByName(name);
    }

    @Override
    public List<Property> GetByYear(Integer year) {
        return propertyMapper.getByYear(year);
    }

    @Override
    public List<Property> nopay() {
        return propertyMapper.nopay();
    }

    @Override
    public void urge() {
        List<PropertyMessage> messages = propertyMapper.urge();
        for (PropertyMessage message : messages) {
            String mess = "尊敬的业主" + message.getName() + "，您" + message.getYear() + "年的物业费共" + message.getPrice() + "元，还未缴纳，请您登陆物业管理缴纳物业费，谢谢";
            System.out.println("用户电话" + message.getPhone() + "--------" + mess);

        }
    }
}
