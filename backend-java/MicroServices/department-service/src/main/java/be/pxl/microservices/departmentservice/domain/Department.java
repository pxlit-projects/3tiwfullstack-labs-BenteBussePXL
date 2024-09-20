package be.pxl.microservices.departmentservice.domain;

import be.pxl.microservices.employee.employeeservice.domain.Employee;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long organizationId;
    private String name;
    @Transient
    private List<Employee> employees;
    private String position;

    public Department() {
    }

    public Department(long organizationId, String name, List<Employee> employees, String position) {
        this.organizationId = organizationId;
        this.name = name;
        this.employees = employees;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
