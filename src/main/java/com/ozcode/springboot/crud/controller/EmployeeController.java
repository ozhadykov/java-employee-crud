package com.ozcode.springboot.crud.controller;

import com.ozcode.springboot.crud.entity.Employee;
import com.ozcode.springboot.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add mapping for listing employees
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get employees from the db
        List<Employee> employees = this.employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", employees);

        return "employees/list";
    }

    // add mapping for adding employee form
    @GetMapping("/add-employee-form")
    public String addEmployeeForm(Model theModel) {
        theModel.addAttribute("employee", new Employee());
        return "employees/add-employee-form";
    }

    // process the post request employee form
    @PostMapping("/process-employee-form")
    public String processEmployeeForm(@ModelAttribute("employee") Employee theEmployee) {
        this.employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }
}
