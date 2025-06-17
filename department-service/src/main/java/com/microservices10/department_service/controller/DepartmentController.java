package com.microservices10.department_service.controller;

import com.microservices10.department_service.client.EmployeeClient;
import com.microservices10.department_service.model.Department;
import com.microservices10.department_service.repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping("")
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department added: {}", department); // Corrected logging
        return repository.addDepartment(department); // Assuming addDepartment is a custom method in your repository
        // If using Spring Data JPA, this would typically be repository.save(department)
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        // Consider adding error handling here if the department is not found
        // e.g., return ResponseEntity.notFound().build(); or throw an exception
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find employees");
        List<Department>departments = repository.findAll();
//        departments.forEach(department -> department.setEmployees(
//                employeeClient.findByDepartment(department.getId())));
        return departments;
    }
}