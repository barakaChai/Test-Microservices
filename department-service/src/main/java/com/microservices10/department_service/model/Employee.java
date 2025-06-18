package com.microservices10.department_service.model;

public record Employee(Long id, Long departmentId, String employeeName,String employeeEmail, String employeePosition) {
}
