package com.ozcode.springboot.crud.service;

import com.ozcode.springboot.crud.dao.EmployeeRepository;
import com.ozcode.springboot.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return this.employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        this.employeeRepository.deleteById(theId);
    }
}
