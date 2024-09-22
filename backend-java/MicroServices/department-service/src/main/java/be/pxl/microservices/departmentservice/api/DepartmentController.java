package be.pxl.microservices.departmentservice.api;

import be.pxl.microservices.departmentservice.api.data.DepartmentDTO;
import be.pxl.microservices.departmentservice.api.data.DepartmentRequest;
import be.pxl.microservices.departmentservice.api.data.DepartmentWOEmployeesDTO;
import be.pxl.microservices.departmentservice.exception.NotFoundException;
import be.pxl.microservices.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        try {
            this.departmentService.createDepartment(departmentRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable long id){
        try {
            DepartmentDTO departmentDTO = this.departmentService.getDepartmentById(id);
            return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments(){
        try {
            List<DepartmentDTO> departmentDTOS = this.departmentService.getAllDepartments();
            return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("organization/{organizationId}")
    public ResponseEntity<?> getDepartmentByOrganization(@PathVariable long organizationId){
        try {
            List<DepartmentWOEmployeesDTO> departmentDTOS = this.departmentService.getDepartmentByOrganization(organizationId);
            return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("organization/{organizationId}/with-employees")
    public ResponseEntity<?> getDepartmentByOrganizationWithEmployees(@PathVariable long organizationId){
        try {
            List<DepartmentDTO> departmentDTOS = this.departmentService.getDepartmentByOrganizationWithEmployees(organizationId);
            return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDepartments(@PathVariable long id, @RequestBody DepartmentDTO departmentDTO){
        try {
            this.departmentService.updateDepartment(id, departmentDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable long id){
        try {
            this.departmentService.deleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
