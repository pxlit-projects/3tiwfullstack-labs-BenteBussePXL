package be.pxl.miccroservices.organizationservice.api.data;

import be.pxl.microservices.departmentservice.domain.Department;
import be.pxl.microservices.employee.employeeservice.domain.Employee;

import java.util.List;

public record OrganizationDTO(long id, String name, String address, List<Employee> employees, List<Department> departments) {
}