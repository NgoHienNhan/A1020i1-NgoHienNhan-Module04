package com.example.demo.service;

import com.example.demo.bean.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Iterable<Employee> findAllNotPage();

    Employee findById(String id);

    void save(Employee employee);

    void delete(Employee employee);
}
