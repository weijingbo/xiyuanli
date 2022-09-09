package com.xiyuanli.service.impl;

import com.xiyuanli.entity.VO.WaterMessage;
import com.xiyuanli.entity.Water;
import com.xiyuanli.mapper.WaterMapper;
import com.xiyuanli.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterMapper waterMapper;

    @Override
    public List<Water> getWater(Integer uid) {
        return waterMapper.getWaters(uid);
    }

    @Override
    public Integer PayWater(Integer id, String flag) {
        return waterMapper.PayWater(id, flag);
    }

    @Override
    public Integer insert(Water water) {
        return waterMapper.insert(water);
    }

    @Override
    public List<Water> selectWaters(String date) {
        return waterMapper.selectByDate(date);
    }

    @Override
    public Integer delete(Integer id) {
        return waterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Water> getWaterByName(String name) {
        return waterMapper.getWaterByName(name);
    }

    @Override
    public List<Water> getNoPay() {
        return waterMapper.nopay();
    }

    @Override
    public void urge() {
        List<WaterMessage> messages = waterMapper.urge();
        for (WaterMessage message : messages) {
            String mess = "尊敬的业主" + message.getName() + "，您" + message.getDate() + "的水费共" + message.getPrice() + "元，还未缴纳，请您登陆物业管理缴纳水费，谢谢";
            System.out.println("用户电话" + message.getPhone() + "--------" + mess);

        }
    }
}
