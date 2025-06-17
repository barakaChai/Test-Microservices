package com.microservices10.department_service.model;

import com.microservices10.department_service.model.Employee;


import java.util.ArrayList;
import java.util.List;


public class Department {
    private Long id;
    private String departmentName;
//    private List<Employee> employees = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", employees=" + employees +
                '}';
    }




}
