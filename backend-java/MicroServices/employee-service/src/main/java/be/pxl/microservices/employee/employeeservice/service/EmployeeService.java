package be.pxl.microservices.employee.employeeservice.service;

import be.pxl.microservices.employee.employeeservice.api.data.EmployeeDTO;
import be.pxl.microservices.employee.employeeservice.api.data.EmployeeRequest;
import be.pxl.microservices.employee.employeeservice.api.data.NotificationRequest;
import be.pxl.microservices.employee.employeeservice.client.NotificationClient;
import be.pxl.microservices.employee.employeeservice.domain.Employee;
import be.pxl.microservices.employee.employeeservice.exception.NotFoundException;
import be.pxl.microservices.employee.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    public void createEmployee(EmployeeRequest employeeRequest){
        Employee newEmployee = new Employee(employeeRequest.organizationId(), employeeRequest.departmentId(), employeeRequest.name(), employeeRequest.age(), employeeRequest.position());
        this.employeeRepository.save(newEmployee);
        notificationClient.sendNotification(new NotificationRequest("New Employee Created", "Employee Service"));
    }

    public Employee findEmployeeById(long id){
        return this.employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " not found!"));
    }

    public EmployeeDTO getEmployeeById(long id){
        Employee employee = findEmployeeById(id);
        return new EmployeeDTO(employee.getId(), employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition());
    }

    public List<EmployeeDTO> getAllEmployees(){
        return this.employeeRepository.findAll()
                .stream().map(employee -> new EmployeeDTO(employee.getId(), employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition())).toList();
    }

    public void updateEmployee(long id, EmployeeDTO employeeDTO){
        Employee employee = findEmployeeById(id);
        employee.setOrganizationId(employeeDTO.organizationId());
        employee.setDepartmentId(employeeDTO.departmentId());
        employee.setName(employeeDTO.name());
        employee.setAge(employeeDTO.age());
        employee.setPosition(employeeDTO.position());
        this.employeeRepository.save(employee);
    }

    public void deleteEmployee(long id){
        Employee employee = findEmployeeById(id);
        this.employeeRepository.delete(employee);
    }

    public List<EmployeeDTO> getEmployeesByDepartment(long departmentId) {
        return this.employeeRepository.findEmployeesByDepartmentId(departmentId)
                .stream().map(employee -> new EmployeeDTO(employee.getId(), employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition())).toList();
    }

    public List<EmployeeDTO> getEmployeesByOrganization(long organizationId) {
        return this.employeeRepository.findEmployeesByOrganizationId(organizationId)
                .stream().map(employee -> new EmployeeDTO(employee.getId(), employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition())).toList();
    }
}
