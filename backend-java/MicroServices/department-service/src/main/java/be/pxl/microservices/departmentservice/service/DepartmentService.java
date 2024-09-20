package be.pxl.microservices.departmentservice.service;

import be.pxl.microservices.departmentservice.api.data.DepartmentDTO;
import be.pxl.microservices.departmentservice.domain.Department;
import be.pxl.microservices.departmentservice.repository.DepartmentRepository;
import be.pxl.microservices.employee.employeeservice.domain.Employee;
import be.pxl.microservices.employee.employeeservice.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void createDepartment(DepartmentDTO departmentDTO){
        Department newDepartment = new Department(departmentDTO.organizationId(),departmentDTO.name(), departmentDTO.employees(), departmentDTO.position());
        this.departmentRepository.save(newDepartment);
    }

    public Department findDepartmentById(long id){
        return this.departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " not found!"));
    }

    public DepartmentDTO getDepartmentById(long id){
        Department department = findDepartmentById(id);
        return new DepartmentDTO(department.getOrganizationId(), department.getName(), department.getEmployees(), department.getPosition());
    }

    public List<DepartmentDTO> getAllDepartments(){
        return this.departmentRepository.findAll()
                .stream().map(department -> new DepartmentDTO(department.getOrganizationId(), department.getName(), department.getEmployees(), department.getPosition())).toList();
    }

    public void updateDepartment(long id, DepartmentDTO departmentDTO){
        Department department = findDepartmentById(id);
        department.setOrganizationId(departmentDTO.organizationId());
        department.setName(departmentDTO.name());
        department.setEmployees(departmentDTO.employees());
        department.setPosition(departmentDTO.position());
        this.departmentRepository.save(department);
    }

    public void deleteDepartment(long id){
        Department department = findDepartmentById(id);
        this.departmentRepository.delete(department);
    }
}
