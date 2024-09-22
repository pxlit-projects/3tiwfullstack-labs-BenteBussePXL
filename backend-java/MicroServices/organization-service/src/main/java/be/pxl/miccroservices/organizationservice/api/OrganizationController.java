package be.pxl.miccroservices.organizationservice.api;

import be.pxl.miccroservices.organizationservice.api.data.*;
import be.pxl.miccroservices.organizationservice.service.OrganizationService;
import be.pxl.microservices.departmentservice.api.data.DepartmentDTO;
import be.pxl.microservices.departmentservice.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationRequest organizationRequest){
        try {
            this.organizationService.createOrganization(organizationRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable long id){
        try {
            OrganizationWOEmployeesAndDepartmentsDTO organizationDTO = this.organizationService.getOrganizationById(id);
            return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}/with-departments")
    public ResponseEntity<?> getOrganizationByIdWithDepartments(@PathVariable long id){
        try {
            OrganizationWOEmployeesDTO organizationDTO = this.organizationService.getOrganizationByIdWithDepartments(id);
            return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}/with-departments-and-employees")
    public ResponseEntity<?> getOrganizationByIdWithDepartmentsAndEmployees(@PathVariable long id){
        try {
            OrganizationDTO organizationDTO = this.organizationService.getOrganizationByIdWithDepartmentsAndEmployees(id);
            return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}/with-employees")
    public ResponseEntity<?> getOrganizationByIdWithEmployees(@PathVariable long id){
        try {
            OrganizationWODepartmentsDTO organizationDTO = this.organizationService.getOrganizationByIdWithEmployees(id);
            return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOrganizations(){
        try {
            List<OrganizationDTO> organizationDTOList = this.organizationService.getAllOrganizations();
            return new ResponseEntity<>(organizationDTOList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable long id, @RequestBody OrganizationDTO organizationDTO){
        try {
            this.organizationService.updateOrganization(id, organizationDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable long id){
        try {
            this.organizationService.deleteOrganization(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
