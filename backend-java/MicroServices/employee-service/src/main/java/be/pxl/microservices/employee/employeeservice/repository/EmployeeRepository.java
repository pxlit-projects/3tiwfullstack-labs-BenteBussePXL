package be.pxl.microservices.employee.employeeservice.repository;

import be.pxl.microservices.employee.employeeservice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByDepartmentId(long departmentId);
    List<Employee> findEmployeesByOrganizationId(long organizationId);
}
