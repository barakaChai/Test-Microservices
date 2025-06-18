package com.microservices10.employee_service.repository;

import com.microservices10.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public Employee add(Employee employee){
        employees.add(employee);
        return employee;
    }

    public Employee findById(Long id){
        return employees.stream()
                .filter(a->a.id()
                .equals(id)).findFirst().orElseThrow();
    }

    public List<Employee> findAll(){
        return employees;
    }

    public List<Employee> findByDepartment(Long departmentId){
        return employees.stream().filter(a->a.departmentId().equals(departmentId)).toList();
    }

    // NEW METHOD TO IMPLEMENT
    public List<Employee> findByDepartmentId(Long departmentId) {
        return employees.stream()
                .filter(employee -> departmentId.equals(employee.departmentId()))
                .collect(Collectors.toList());
    }

    public List<Employee> findByDepartmentIdIn(List<Long> ids) {
        return employees.stream()
                .filter(employee -> ids.contains(employee.departmentId()))
                .collect(Collectors.toList());
    }
}
