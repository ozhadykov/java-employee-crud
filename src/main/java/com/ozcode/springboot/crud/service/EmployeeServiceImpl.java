package com.ozcode.springboot.crud.service;

import com.ozcode.springboot.crud.dao.EmployeeDAO;
import com.ozcode.springboot.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {

        return this.employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return this.employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        this.employeeDAO.deleteById(theId);
    }
}
