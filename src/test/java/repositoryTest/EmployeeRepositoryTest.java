package repositoryTest;


import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.repo.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employeeRepo.save(employee);
    }

    @Test
    void testFindById() {
        Employee found = employeeRepo.findById(employee.getEmployeeId()).orElse(null);
        assertNotNull(found);
        assertEquals(employee.getFirstName(), found.getFirstName());
    }

    @Test
    void testSearchEmployees() {
        List<Employee> employees = employeeRepo.searchEmployees("John");
        assertFalse(employees.isEmpty());
    }

}
