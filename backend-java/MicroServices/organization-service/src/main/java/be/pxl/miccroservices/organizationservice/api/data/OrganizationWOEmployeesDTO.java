package be.pxl.miccroservices.organizationservice.api.data;

import be.pxl.microservices.departmentservice.domain.Department;
import be.pxl.microservices.employee.employeeservice.domain.Employee;

import java.util.List;

public record OrganizationWOEmployeesDTO(long id, String name, String address, List<Department> departments) {
}