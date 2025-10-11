package com.ozcode.springboot.crud.dao;

import com.ozcode.springboot.crud.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
