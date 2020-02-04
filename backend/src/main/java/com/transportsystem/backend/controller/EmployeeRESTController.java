package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transportsystem.backend.model.Employee;
import com.transportsystem.backend.service.EmployeeService;
import com.transportsystem.backend.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class EmployeeRESTController {
    private EmployeeService employeeService;
    private JsonService jsonService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/employees")
    public List<Employee> readAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee readEmployes(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }

    @PostMapping(value = "/employees/add", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody String data) throws JsonProcessingException {
        Employee employee = jsonService.getObjectMapper().readValue(data,Employee.class);
        employeeService.add(employee);
        return employee;
    }

    @PutMapping(value = "employees/edit", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee updateEmployee(@RequestBody String data) throws JsonProcessingException {
        Employee employee = jsonService.getObjectMapper().readValue(data,Employee.class);
        employeeService.edit(employee);
        return employee;
    }

    @DeleteMapping("employees/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.delete(employee);
    }
}
