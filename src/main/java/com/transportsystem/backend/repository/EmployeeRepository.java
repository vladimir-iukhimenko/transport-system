package com.transportsystem.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.transportsystem.backend.model.Employee;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
