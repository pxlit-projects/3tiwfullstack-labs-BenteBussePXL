package be.pxl.microservices.departmentservice.api;

import be.pxl.microservices.departmentservice.api.data.DepartmentDTO;
import be.pxl.microservices.departmentservice.api.data.DepartmentRequest;
import be.pxl.microservices.departmentservice.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.junit.jupiter.Container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class DepartmentControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Container
    private static MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("testdepartmentdb");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",() -> sqlContainer.getJdbcUrl());
        registry.add("spring.datasource.username",() -> sqlContainer.getUsername());
        registry.add("spring.datasource.password",() -> sqlContainer.getPassword());
    }

    @Test
    void createNewDepartmentShouldReturnCreatedStatusTest() throws Exception {
        DepartmentRequest department = new DepartmentRequest(5, "IT", null, "Corda 2");

        String departmentRequest = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(departmentRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllDepartmentsShouldReturnAllDepartmentsTest() throws Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO(1, 5, "IT", null, "Corda 2");
        List<DepartmentDTO> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(departmentDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedDepartments)));
    }

    @Test
    void getDepartmentByIdShouldReturnDepartmentTest() throws Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO(1, 5, "IT", null, "Corda 2");
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(departmentDTO)));
    }

    @Test
    void getDepartmentByOrganizationIdShouldReturnDepartmentTest() throws Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO(1, 5, "IT", null, "Corda 2");
        List<DepartmentDTO> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(departmentDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/organization/5"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedDepartments)));
    }
}
