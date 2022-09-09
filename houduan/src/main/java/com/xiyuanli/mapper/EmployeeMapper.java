package com.xiyuanli.mapper;

import com.xiyuanli.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee selectByName(String name);

    List<Employee> select(String name);

    List<String> getNames();
}