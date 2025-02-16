package repositoryTest;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.repo.DepartmentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepo departmentRepo;

    private Department department;
    private Project project1;
    private Project project2;

    @BeforeEach
    void setUp() {
        project1 = new Project();
        project1.setBudget(50000.0);

        project2 = new Project();
        project2.setBudget(50000.0);

        department = new Department();
        department.setBudget(100000.0);
        department.setProjectList(Arrays.asList(project1, project2));

        departmentRepo.save(department);
    }

    @Test
    void testGetTotalBudgetForDepartment() {
        Double totalBudget = departmentRepo.getTotalBudgetForDepartment(department.getDepartmentId());

        assertNotNull(totalBudget);
        assertEquals(100000.0, totalBudget);
    }

    @Test
    void testGetProjectsByDepartment() {
        List<Project> projects = departmentRepo.getProjectsByDepartment(department.getDepartmentId());

        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertTrue(projects.contains(project1));
        assertTrue(projects.contains(project2));
    }
}
