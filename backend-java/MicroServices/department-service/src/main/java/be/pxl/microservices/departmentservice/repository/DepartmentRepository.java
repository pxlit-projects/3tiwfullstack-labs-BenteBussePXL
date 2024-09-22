package be.pxl.microservices.departmentservice.repository;

import be.pxl.microservices.departmentservice.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentByOrganizationId(long organizationId);
}
