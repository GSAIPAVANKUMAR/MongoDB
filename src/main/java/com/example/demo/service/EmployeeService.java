package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    public Employee addEmployee(Employee e) {
        e.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
        return repo.save(e);
    }

    public List<Employee> findAllEmployees() {
        return repo.findAll();
    }

    public Employee findEmployeeByEmployeeName(String employeeName){
        return repo.findByEmployeeName(employeeName);
    }

    public Employee findEmployeeByEmployeeId(String employeeId){
        return repo.findByEmployeeId(employeeId);
    }

    public Employee updateEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Boolean deleteEmployee(String employeeName){
        return repo.deleteByEmployeeName(employeeName);
    }

    public List<Employee> getEmployeesBySalary(float salary){
        return repo.getEmployees(salary);
    }

    public List<Employee> fetchEmployees(float salary,int rating){
        return repo.fetchEmployees(salary,rating);
    }
}
