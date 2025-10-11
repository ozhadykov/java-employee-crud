package com.ozcode.springboot.crud.dao;

import com.ozcode.springboot.crud.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJapImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeDAOJapImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = this.entityManager.createQuery("from Employee ", Employee.class);

        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int theId) {

        Employee theEmployee = this.entityManager.find(Employee.class, theId);

        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        // merge updates if id != 0 and saves/inserts if id == 0
        Employee dbEmployee = this.entityManager.merge(employee);

        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        Employee dbEmployee = this.entityManager.find(Employee.class, theId);
        this.entityManager.remove(dbEmployee);
    }
}
