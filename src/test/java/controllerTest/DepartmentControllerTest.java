package controllerTest;

import com.airtribe.EmployeeTrackingSystem.controller.DepartmentController;
import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new DepartmentController(departmentService)).build();
    }

    @Test
    void testGetAllDepartments() throws Exception {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentService.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(departments.size()));

        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetDepartmentById_Authenticated() throws Exception {
        int departmentId = 1;
        Department department = new Department();
        when(departmentService.getDepartmentById(departmentId)).thenReturn(department);

        mockMvc.perform(get("/api/departments/{departmentId}", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(department.getDepartmentId()));

        verify(departmentService, times(1)).getDepartmentById(departmentId);
    }

    @Test
    void testGetDepartmentById_Unauthenticated() throws Exception {
        int departmentId = 1;

        mockMvc.perform(get("/api/departments/{departmentId}", departmentId))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateDepartment() throws Exception {
        Department department = new Department();
        when(departmentService.addDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"HR\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("HR"));

        verify(departmentService, times(1)).addDepartment(any(Department.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateDepartment() throws Exception {
        int departmentId = 1;
        Department department = new Department();
        when(departmentService.updateDepartment(eq(departmentId), any(Department.class))).thenReturn(department);

        mockMvc.perform(put("/api/departments/{departmentId}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"HR\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"));

        verify(departmentService, times(1)).updateDepartment(eq(departmentId), any(Department.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteDepartment() throws Exception {
        int departmentId = 1;

        mockMvc.perform(delete("/api/departments/{departmentId}", departmentId))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).deleteDepartment(departmentId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetTotalBudget() throws Exception {
        int departmentId = 1;
        Double totalBudget = 100000.0;
        when(departmentService.getTotalBudegt(departmentId)).thenReturn(totalBudget);

        mockMvc.perform(get("/api/departments/totalBudget/{departmentId}", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(totalBudget));

        verify(departmentService, times(1)).getTotalBudegt(departmentId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetProjectsByDepartment() throws Exception {
        int departmentId = 1;
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(departmentService.getProjectsByDepartment(departmentId)).thenReturn(projects);

        mockMvc.perform(get("/api/departments/{departmentId}/projects", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(projects.size()));

        verify(departmentService, times(1)).getProjectsByDepartment(departmentId);
    }
}
