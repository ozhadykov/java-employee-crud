package com.ozcode.springboot.crud.service;

import com.ozcode.springboot.crud.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    Employee save(Employee employee);

    void deleteById(int theId);
}
