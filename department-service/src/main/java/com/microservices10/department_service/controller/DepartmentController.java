package com.microservices10.department_service.controller;

import com.microservices10.department_service.client.EmployeeClient;
import com.microservices10.department_service.model.Department;
import com.microservices10.department_service.model.Employee;
import com.microservices10.department_service.repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Find Department Employees");
        List<Department> departments = repository.findAll();

        // 1. Get all department IDs
        List<Long> departmentIds = departments.stream()
                .map(Department::getId)
                .collect(Collectors.toList());

        // 2. Make ONE network call to get all employees for those departments
        List<Employee> allEmployees = employeeClient.findByDepartmentIds(departmentIds);

        // 3. Map the employees back to their departments in memory
        departments.forEach(department ->
                department.setEmployees(
                        allEmployees.stream()
                                // Use the correct record accessor method: departmentId()
                                .filter(employee -> employee.departmentId().equals(department.getId()))
                                .collect(Collectors.toList())
                )
        );
        return departments;
    }

}