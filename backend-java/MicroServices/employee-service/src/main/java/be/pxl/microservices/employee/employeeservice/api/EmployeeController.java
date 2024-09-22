package be.pxl.microservices.employee.employeeservice.api;

import be.pxl.microservices.employee.employeeservice.api.data.EmployeeDTO;
import be.pxl.microservices.employee.employeeservice.api.data.EmployeeRequest;
import be.pxl.microservices.employee.employeeservice.exception.NotFoundException;
import be.pxl.microservices.employee.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        try {
            this.employeeService.createEmployee(employeeRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id){
        try {
            EmployeeDTO employeeDTO = this.employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        try {
            List<EmployeeDTO> employeeDTOList = this.employeeService.getAllEmployees();
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("department/{departmentId}")
    public ResponseEntity<?> findByDepartment(@PathVariable long departmentId){
        try {
            List<EmployeeDTO> employeeDTOList = this.employeeService.getEmployeesByDepartment(departmentId);
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("organization/{organizationId}")
    public ResponseEntity<?> findByOrganization(@PathVariable long organizationId){
        try {
            List<EmployeeDTO> employeeDTOList = this.employeeService.getEmployeesByOrganization(organizationId);
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO){
        try {
            this.employeeService.updateEmployee(id, employeeDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id){
        try {
            this.employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
