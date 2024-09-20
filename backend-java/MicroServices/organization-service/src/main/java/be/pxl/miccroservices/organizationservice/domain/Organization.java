package be.pxl.miccroservices.organizationservice.domain;

import be.pxl.microservices.departmentservice.domain.Department;
import be.pxl.microservices.employee.employeeservice.domain.Employee;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    @Transient
    private List<Employee> employees;
    @Transient
    private List<Department> departments;

    public Organization() {
    }

    public Organization(String name, String address, List<Employee> employees, List<Department> departments) {
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.departments = departments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
