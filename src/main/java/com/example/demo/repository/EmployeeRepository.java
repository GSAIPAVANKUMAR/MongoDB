package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee,String> {

    Employee findByEmployeeName(String employeeName);

    Employee findByEmployeeId(String employeeName);

    Boolean deleteByEmployeeName(String employeeName);

    @Query("{salary: {$gt:?0}}")
    List<Employee> getEmployees(float salary);
}
