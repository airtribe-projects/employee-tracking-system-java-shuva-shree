package serviceTest;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.repo.DepartmentRepo;
import com.airtribe.EmployeeTrackingSystem.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentRepo.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(departments.size(), result.size());
        verify(departmentRepo, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById_DepartmentExists() {
        int departmentId = 1;
        Department department = new Department();
        when(departmentRepo.findById(departmentId)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartmentById(departmentId);

        assertEquals(department, result);
        verify(departmentRepo, times(1)).findById(departmentId);
    }

    @Test
    void testGetDepartmentById_DepartmentNotFound() {
        int departmentId = 1;
        when(departmentRepo.findById(departmentId)).thenReturn(Optional.empty());

        Department result = departmentService.getDepartmentById(departmentId);

        assertNull(result);
        verify(departmentRepo, times(1)).findById(departmentId);
    }

    @Test
    void testAddDepartment() {
        Department department = new Department();
        when(departmentRepo.save(department)).thenReturn(department);

        Department result = departmentService.addDepartment(department);

        assertEquals(department, result);
        verify(departmentRepo, times(1)).save(department);
    }

    @Test
    void testUpdateDepartment_DepartmentExists() {
        int departmentId = 1;
        Department existingDepartment = new Department();
        Department updatedDepartment = new Department();
        updatedDepartment.setDepartmentName("HR");
        updatedDepartment.setDepartmentHead("John Doe");


        when(departmentRepo.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(departmentRepo.save(existingDepartment)).thenReturn(existingDepartment);

        Department result = departmentService.updateDepartment(departmentId, updatedDepartment);

        assertEquals(updatedDepartment.getDepartmentName(), result.getDepartmentName());
        assertEquals(updatedDepartment.getDepartmentHead(), result.getDepartmentHead());


        verify(departmentRepo, times(1)).findById(departmentId);
        verify(departmentRepo, times(1)).save(existingDepartment);
    }

    @Test
    void testUpdateDepartment_DepartmentNotFound() {
        int departmentId = 1;
        Department updatedDepartment = new Department();
        when(departmentRepo.findById(departmentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            departmentService.updateDepartment(departmentId, updatedDepartment);
        });

        assertEquals("Department not found", exception.getMessage());
        verify(departmentRepo, times(1)).findById(departmentId);
        verify(departmentRepo, times(0)).save(any(Department.class));
    }

    @Test
    void testDeleteDepartment() {
        int departmentId = 1;

        departmentService.deleteDepartment(departmentId);

        verify(departmentRepo, times(1)).deleteById(departmentId);
    }

    @Test
    void testGetTotalBudget() {
        int departmentId = 1;
        Double totalBudget = 100000.0;
        when(departmentRepo.getTotalBudgetForDepartment(departmentId)).thenReturn(totalBudget);

        Double result = departmentService.getTotalBudegt(departmentId);

        assertEquals(totalBudget, result);
        verify(departmentRepo, times(1)).getTotalBudgetForDepartment(departmentId);
    }

    @Test
    void testGetProjectsByDepartment() {
        int departmentId = 1;
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(departmentRepo.getProjectsByDepartment(departmentId)).thenReturn(projects);

        List<Project> result = departmentService.getProjectsByDepartment(departmentId);

        assertEquals(projects.size(), result.size());
        verify(departmentRepo, times(1)).getProjectsByDepartment(departmentId);
    }
}
