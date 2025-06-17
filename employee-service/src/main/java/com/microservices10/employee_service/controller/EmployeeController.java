package com.microservices10.employee_service.controller;

import com.microservices10.employee_service.model.Employee;
import com.microservices10.employee_service.repository.EmployeeRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee){
        LOGGER.info("Employee add: {}", employee);
        return repository.add(employee); // Assuming 'add' is the method in your repository
    }

    @GetMapping
    public List<Employee> findAll(){
        LOGGER.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id){ // Changed to ResponseEntity
        LOGGER.info("Employee find: id={}",id);
        Employee employee = repository.findById(id); // Assuming findById returns null or throws if not found
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            // If findById throws an exception when not found, this part might be handled by an exception handler
            // or the exception itself would lead to a 500 if findById doesn't return null but throws.
            // For robustness, ensure findById either returns null or handle its specific exception.
            return ResponseEntity.notFound().build();
        }
    }

    // CORRECTED METHOD
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId){ // Renamed for clarity and changed return type
        LOGGER.info("Finding employees for departmentId: {}", departmentId);
        return repository.findByDepartmentId(departmentId); // Call the new repository method
    }
}