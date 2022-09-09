package com.xiyuanli.controller;

import com.xiyuanli.entity.Employee;
import com.xiyuanli.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/manage")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/login")
    public HashMap Login(@RequestParam("name") String name, @RequestParam("password") String password) {
        Boolean ismanage = false;
        HashMap hashMap = new HashMap<>();
        Employee employee = employeeService.Login(name);
        if (employee != null) {
            if (employee.getPassword().equals(password)) {
                ismanage = true;
            }
        }
        hashMap.put("ismanage", ismanage);
        hashMap.put("employee", employee);
        return hashMap;
    }

    @RequestMapping("/getEmployees")
    public List<Employee> GetEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getName().equals("admin")) {
                iterator.remove();
            }
        }
        return employees;
    }

    @RequestMapping("/getEmployee")
    public List<Employee> GetEmployee(@RequestParam("name") String name) {
        return employeeService.getEmployee(name);

    }

    @RequestMapping("/insertEmployee")
    public Integer InsertEmployee(@RequestBody Employee employee) {
        return employeeService.insert(employee);
    }

    @RequestMapping("/updateEmployee")
    public Integer UpdateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @RequestMapping("/deleteEmployee")
    public Integer DeleteEmployee(@RequestParam("id") Integer id) {
        return employeeService.delete(id);
    }

    @RequestMapping("/getNames")
    public List<String> getNames() {
        List<String> names = employeeService.getNames();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.equals("admin")) {
                iterator.remove();
            }
        }
        return names;
    }
}

