package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.EmployeeRepository;
import com.transportsystem.backend.model.Employee;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeDAO(EmployeeRepository employeeRepository) {this.employeeRepository = employeeRepository;}

    @Transactional
    public void add(Employee employee) {
        employeeRepository.save(employee);}

    @Transactional
    public void delete(Employee employee) {
        employeeRepository.delete(employee);}

    @Transactional
    public void edit(Employee employee) {
        employeeRepository.save(employee);}

    @Transactional
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Employee> getAllEmployees() {return employeeRepository.findAll();}


}
