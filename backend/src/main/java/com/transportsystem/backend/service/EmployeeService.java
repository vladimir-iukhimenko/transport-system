package com.transportsystem.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.dao.EmployeeDAO;
import com.transportsystem.backend.model.Employee;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */

@Service
public class EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}

    @Transactional
    public void add(Employee employee) {employeeDAO.add(employee);}

    @Transactional
    public void delete(Employee employee) {employeeDAO.delete(employee);}

    @Transactional
    public void edit(Employee employee) {employeeDAO.edit(employee);}

    @Transactional
    public Employee getEmployeeById(Integer id) {return employeeDAO.getEmployeeById(id);}

    @Transactional
    public List<Employee> getAllEmployees() {return employeeDAO.getAllEmployees();}


}
