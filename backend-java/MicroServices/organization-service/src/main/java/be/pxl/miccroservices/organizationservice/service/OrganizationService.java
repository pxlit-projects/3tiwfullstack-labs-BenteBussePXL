package be.pxl.miccroservices.organizationservice.service;

import be.pxl.miccroservices.organizationservice.api.data.OrganizationDTO;
import be.pxl.miccroservices.organizationservice.domain.Organization;
import be.pxl.miccroservices.organizationservice.repository.OrganizationRepository;
import be.pxl.microservices.departmentservice.api.data.DepartmentDTO;
import be.pxl.microservices.departmentservice.domain.Department;
import be.pxl.microservices.employee.employeeservice.exception.NotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    public void createOrganization(OrganizationDTO organizationDTO){
        Organization newOrganization = new Organization(organizationDTO.name(), organizationDTO.address(), organizationDTO.employees(), organizationDTO.departments());
        this.organizationRepository.save(newOrganization);
    }

    public Organization findOrganizationById(long id){
        return this.organizationRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " not found!"));
    }

    public OrganizationDTO getOrganizationById(long id){
        Organization organization = findOrganizationById(id);
        return new OrganizationDTO(organization.getName(), organization.getAddress(), organization.getEmployees(), organization.getDepartments());
    }

    public List<OrganizationDTO> getAllOrganizations(){
        return this.organizationRepository.findAll()
                .stream().map(organization -> new OrganizationDTO(organization.getName(), organization.getAddress(), organization.getEmployees(), organization.getDepartments())).toList();
    }

    public void updateOrganization(long id, OrganizationDTO organizationDTO){
        Organization organization = findOrganizationById(id);
        organization.setName(organizationDTO.name());
        organization.setAddress(organizationDTO.address());
        organization.setEmployees(organizationDTO.employees());
        organization.setDepartments(organizationDTO.departments());
        this.organizationRepository.save(organization);
    }

    public void deleteOrganization(long id){
        Organization organization = findOrganizationById(id);
        this.organizationRepository.delete(organization);
    }
}
