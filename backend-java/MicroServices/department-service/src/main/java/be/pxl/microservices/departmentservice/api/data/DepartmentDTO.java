package be.pxl.microservices.departmentservice.api.data;

import be.pxl.microservices.employee.employeeservice.domain.Employee;

import java.util.List;

public record DepartmentDTO(long id, long organizationId, String name, List<Employee> employees, String position) {
}
