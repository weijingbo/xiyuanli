package com.xiyuanli.service.impl;

import com.xiyuanli.entity.Employee;
import com.xiyuanli.mapper.EmployeeMapper;
import com.xiyuanli.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee Login(String name) {
        return employeeMapper.selectByName(name);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.selectAll();
    }

    @Override
    public List<Employee> getEmployee(String name) {
        return employeeMapper.select(name);
    }

    @Override
    public Integer insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public Integer delete(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public List<String> getNames() {
        return employeeMapper.getNames();
    }
}
