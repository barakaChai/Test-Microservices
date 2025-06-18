package com.microservices10.department_service.client;

import com.microservices10.department_service.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("http://employee-service/employee/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);

    // In your EmployeeClient interface
    @GetExchange("http://employee-service/employee/by-department-ids")
    List<Employee> findByDepartmentIds(@RequestParam("ids") List<Long> ids);
}
