package com.ozcode.springboot.crud.dao;

import com.ozcode.springboot.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
