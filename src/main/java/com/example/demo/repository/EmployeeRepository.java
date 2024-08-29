package com.example.demo.repository;

import com.example.demo.dto.AverageSalary;
import com.example.demo.dto.NumberEmployees;
import com.example.demo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Aggregation;
import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findByEmployeeName(String employeeName);

    Employee findByEmployeeId(String employeeName);


    Boolean deleteByEmployeeName(String employeeName);

    @Query("{salary: {$gt:?0}}")
    List<Employee> getEmployees(float salary);

    @Query("{ $and: [ { salary: { $gte: ?0 } }, { rating: { $gte: ?1 } } ] }")
    List<Employee> fetchEmployees(float salary, int rating);

    @Aggregation(pipeline = {
            "{$match: {rating: {$eq: ?0}}}",
            "{$group: {_id: '$rating', avgSalary: {$avg: '$salary'}}}",
            "{$project: {_id: 0, rating: '$_id', avgSalary: 1}}"
    })
    List<AverageSalary> getAvgSalary(int rating);

    @Aggregation(pipeline = {"{$match:{rating:{$gt:0}}}","{$group:{_id:'$rating',totalEmp:{$sum:1}}}","{$project:{_id:0,rating:'$_id',TotalEmployees:'$totalEmp'}}"}
    )
    List<NumberEmployees> getRatingNumber();
}

