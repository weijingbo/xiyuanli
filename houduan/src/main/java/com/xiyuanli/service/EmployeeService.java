package com.xiyuanli.service;

import com.xiyuanli.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee Login(String name);

    public List<Employee> getEmployees();

    public List<Employee> getEmployee(String name);

    public Integer insert(Employee employee);

    public Integer delete(Integer id);

    public Integer update(Employee employee);

    public List<String> getNames();
}
