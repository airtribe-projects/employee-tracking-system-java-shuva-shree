package serviceTest;


import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.repo.EmployeeRepo;
import com.airtribe.EmployeeTrackingSystem.service.EmployeeService;
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
    public class EmployeeServiceTest {

        @Mock
        private EmployeeRepo employeeRepo;

        @InjectMocks
        private EmployeeService employeeService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testGetAllEmployees() {
            List<Employee> employees = Arrays.asList(new Employee(), new Employee());
            when(employeeRepo.findAll()).thenReturn(employees);

            List<Employee> result = employeeService.getAllEmployees();

            assertEquals(employees.size(), result.size());
            verify(employeeRepo, times(1)).findAll();
        }

        @Test
        void testAddEmployee() {
            Employee employee = new Employee();
            when(employeeRepo.save(employee)).thenReturn(employee);

            Employee result = employeeService.addEmployee(employee);

            assertEquals(employee, result);
            verify(employeeRepo, times(1)).save(employee);
        }

        @Test
        void testGetEmployeeById_EmployeeExists() {
            int employeeId = 1;
            Employee employee = new Employee();
            when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));

            Employee result = employeeService.getEmployeeById(employeeId);

            assertEquals(employee, result);
            verify(employeeRepo, times(1)).findById(employeeId);
        }

        @Test
        void testGetEmployeeById_EmployeeNotFound() {
            int employeeId = 1;
            when(employeeRepo.findById(employeeId)).thenReturn(Optional.empty());

            Exception exception = assertThrows(RuntimeException.class, () -> {
                employeeService.getEmployeeById(employeeId);
            });

            assertEquals("Employee not found with ID: 1", exception.getMessage());
            verify(employeeRepo, times(1)).findById(employeeId);
        }

        @Test
        void testUpdateEmployee_EmployeeExists() {
            int employeeId = 1;
            Employee existingEmployee = new Employee();
            Employee updatedEmployee = new Employee();
            updatedEmployee.setFirstName("John");
            updatedEmployee.setLastName("Doe");
            // Set other fields...

            when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
            when(employeeRepo.save(existingEmployee)).thenReturn(existingEmployee);

            Employee result = employeeService.updateEmployee(employeeId, updatedEmployee);

            assertEquals(updatedEmployee.getFirstName(), result.getFirstName());
            assertEquals(updatedEmployee.getLastName(), result.getLastName());
            // Assert other fields...

            verify(employeeRepo, times(1)).findById(employeeId);
            verify(employeeRepo, times(1)).save(existingEmployee);
        }

        @Test
        void testUpdateEmployee_EmployeeNotFound() {
            int employeeId = 1;
            Employee updatedEmployee = new Employee();
            when(employeeRepo.findById(employeeId)).thenReturn(Optional.empty());

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                employeeService.updateEmployee(employeeId, updatedEmployee);
            });

            assertEquals("Employee not found", exception.getMessage());
            verify(employeeRepo, times(1)).findById(employeeId);
            verify(employeeRepo, times(0)).save(any(Employee.class));
        }

        @Test
        void testDeleteEmployee_EmployeeExists() {
            int employeeId = 1;
            Employee employee = new Employee();
            when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));

            employeeService.deleteEmployee(employeeId);

            verify(employeeRepo, times(1)).findById(employeeId);
            verify(employeeRepo, times(1)).delete(employee);
        }

        @Test
        void testSearchEmployeeByKeyword() {
            String keyword = "John";
            List<Employee> employees = Arrays.asList(new Employee(), new Employee());
            when(employeeRepo.searchEmployees(keyword)).thenReturn(employees);

            List<Employee> result = employeeService.searchEmployeeByKeyword(keyword);

            assertEquals(employees.size(), result.size());
            verify(employeeRepo, times(1)).searchEmployees(keyword);
        }

        @Test
        void testGetEmployeesInProject() {
            int projectId = 1;
            List<Employee> employees = Arrays.asList(new Employee(), new Employee());
            when(employeeRepo.getEmployeesInProject(projectId)).thenReturn(employees);

            List<Employee> result = employeeService.getEmployeesInProject(projectId);

            assertEquals(employees.size(), result.size());
            verify(employeeRepo, times(1)).getEmployeesInProject(projectId);
        }

        @Test
        void testGetEmployeesWithoutProjects() {
            List<Employee> employees = Arrays.asList(new Employee(), new Employee());
            when(employeeRepo.findEmployeesWithoutProjects()).thenReturn(employees);

            List<Employee> result = employeeService.getEmployeesWithoutProjects();

            assertEquals(employees.size(), result.size());
            verify(employeeRepo, times(1)).findEmployeesWithoutProjects();
        }
    }

