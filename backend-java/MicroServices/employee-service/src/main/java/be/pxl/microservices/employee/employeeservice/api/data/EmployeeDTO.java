package be.pxl.microservices.employee.employeeservice.api.data;

public record EmployeeDTO(long organizationId, long departmentId, String name, int age, String position) {
}
