package be.pxl.microservices.employee.employeeservice.api.data;

public record EmployeeRequest(long organizationId, long departmentId, String name, int age, String position) {
}
