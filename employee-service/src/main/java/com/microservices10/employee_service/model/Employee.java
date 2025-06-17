package com.microservices10.employee_service.model;

public record Employee(Long id, Long departmentId, String employeeName,String employeeEmail, String employeePosition) {
}

