package com.ozcode.springboot.crud.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ozcode.springboot.crud.entity.Employee;
import com.ozcode.springboot.crud.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    public EmployeeRestController(EmployeeService employeeService, ObjectMapper theObjectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = theObjectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {

        Employee theEmployee = this.employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }

        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        // setting id to 0, to force save
        employee.setId(0);

        Employee dbEmployee = this.employeeService.save(employee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        Employee dbEmployee = this.employeeService.save(employee);

        return dbEmployee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> payload) {

        Employee theTempEmployee = this.employeeService.findById(employeeId);
        if (theTempEmployee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }

        if (payload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed to be updated");
        }

        Employee patchedEmployee = apply(payload, theTempEmployee);

        return this.employeeService.save(patchedEmployee);
    }

    private Employee apply(Map<String, Object> payload, Employee tempEmployee) {
        // convert employee object to a JSON object node
        ObjectNode employeeNode = this.objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // convert the payload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(payload, ObjectNode.class);

        // merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee theEmployee = this.employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }

        this.employeeService.deleteById(employeeId);

        return "Deleted employee with id: " + employeeId;
    }
}
