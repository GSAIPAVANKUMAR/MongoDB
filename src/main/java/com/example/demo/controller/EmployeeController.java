package com.example.demo.controller;

import com.example.demo.dto.AverageSalary;
import com.example.demo.dto.AvgDto;
import com.example.demo.dto.EmpSalaryDto;
import com.example.demo.dto.NumberEmployees;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("/employees")
@ResponseStatus(HttpStatus.CREATED)
public class EmployeeController {

    private Logger logger;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add-employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/get-employee/{name}")
    public Employee createEmployee(@PathVariable("name") String name) {
        Employee e = employeeService.findEmployeeByEmployeeName(name);
        if (Objects.equals(e.getEmployeeName(), name)) {
            System.out.println("record found" + e.getEmployeeName());
        }
        return e;
    }

    @PatchMapping("/update-employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee e = employeeService.findEmployeeByEmployeeName(employee.getEmployeeName());
        if (e != null) {
            employee.setEmployeeId(e.getEmployeeId());
            return employeeService.updateEmployee(employee);
        } else {
            employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
            return employeeService.updateEmployee(employee);
        }
    }

    @DeleteMapping("/delete-employee/{name}")
    public Boolean deleteEmployee(@PathVariable("name") String name) {
        System.out.println("employeeName: " + name);
        Employee e = employeeService.findEmployeeByEmployeeName(name);
        if (e != null) {
            Boolean b = employeeService.deleteEmployee(name);
            return b;
        } else {
            return false;
        }
    }

    @PostMapping("/get-employees-by-salary")
    public List<Employee> getEmployeeBySalary(@RequestBody EmpSalaryDto empSalaryDto) {
        return employeeService.getEmployeesBySalary(empSalaryDto.getSalary());
    }


    @GetMapping("/fetch-employees/{salary}/{rating}")
    public List<Employee> searchEmployee(@PathVariable("salary") float salary, @PathVariable("rating") int rating) {
        List<Employee> e = employeeService.fetchEmployees(salary, rating);
        return e;
    }

    @PostMapping("/get-avg-salary")
    public List<AverageSalary> getAverageSalary(@RequestBody AvgDto avgDto) {
        List<AverageSalary> avgSalary = employeeService.getAverageSalary(avgDto.getRating());
        return avgSalary;
    }

    @GetMapping("/get-number-of-employees-by-rating")
    public List<NumberEmployees> getTotalEmployees() {

        List<NumberEmployees> ne = employeeService.getRatingNumber();
        return ne;
    }

}
