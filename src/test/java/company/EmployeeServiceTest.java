package company;

import company.employees.Designer;
import company.employees.Developer;
import company.employees.Employee;
import company.employees.Manager;
import company.service.EmployeeServiceArray;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static company.enums.Gender.FEMALE;
import static company.enums.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private static final EmployeeFactory employeeFactory = new EmployeeFactory();
    private final Designer designerUnderTest =
            new Designer("DesignerName", 25, 1000, FEMALE, 500, 5);
    private final Manager managerUnderTest =
            new Manager("ManagerName", 35, 10000, FEMALE);
    private final Developer developerUnderTest =
            new Developer("DeveloperName", 40, 8000, FEMALE, 4);
    private static EmployeeServiceArray employeeService;

    @Test
    void testCalculateSalaryAndBonus() {
        double expectedResult = designerUnderTest.getFinalSalary() + managerUnderTest.getFinalSalary();

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest});
        double actualResult = employeeService.calculateSalaryAndBonus();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetById() {
        Employee employee = employeeFactory.generateEmployee();
        long id = 500;
        employee.setId(id);

        employeeService = new EmployeeServiceArray(
                new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest, employee});
        Employee actualEmployee = employeeService.getById(id);

        assertEquals(employee, actualEmployee);
    }

    @Test
    void testGetByInvalidId() {
        long invalidId = -1;

        employeeService = new EmployeeServiceArray(
                new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee actualEmployee = employeeService.getById(invalidId);

        assertNull(actualEmployee);
    }

    @Test
    void testGetByNameOneResult() {
        String nameForSearch = designerUnderTest.getName();

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee[] actualEmployees = employeeService.getByName(nameForSearch);

        assertEquals(1, actualEmployees.length);
        assertEquals(designerUnderTest, actualEmployees[0]);
    }

    @Test
    void testGetByNameMultipleResult() {
        String nameForSearch = managerUnderTest.getName();
        Employee employee = new Developer(nameForSearch, 50, 6000, MALE, 5);

        employeeService = new EmployeeServiceArray(
                new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest, employee});
        Employee[] actualEmployees = employeeService.getByName(nameForSearch);

        assertEquals(2, actualEmployees.length);
        assertTrue(Arrays.asList(actualEmployees).contains(employee));
        assertTrue(Arrays.asList(actualEmployees).contains(managerUnderTest));
    }

    @Test
    void testGetByNameNegativeResult() {
        String nameForSearch = "Not Existing";

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee[] actualEmployees = employeeService.getByName(nameForSearch);

        assertEquals(0, actualEmployees.length);
    }

    @Test
    void testSortByName() {
        Employee employee1 = new Developer("Aa", 50, 6000, MALE, 5);
        Employee employee2 = new Developer("Bb", 50, 6000, MALE, 5);
        Employee employee3 = new Developer("Ba", 50, 6000, MALE, 5);
        Employee[] expectedEmployees = new Employee[]{employee1, employee3, employee2};

        employeeService = new EmployeeServiceArray(new Employee[]{employee1, employee2, employee3});
        Employee[] actualEmployees = employeeService.sortByName();

        assertArrayEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void testSortByNameAndSalary() {
        Employee employee1 = new Developer("A", 50, 6000, MALE, 5);
        Employee employee2 = new Developer("B", 50, 6000, MALE, 5);
        Employee employee3 = new Developer("B", 50, 8000, MALE, 5);
        Employee[] expectedEmployees = new Employee[]{employee1, employee3, employee2};

        employeeService = new EmployeeServiceArray(new Employee[]{employee1, employee2, employee3});
        Employee[] actualEmployees = employeeService.sortByNameAndSalary();

        assertArrayEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void testEdit() {
        Employee employee = employeeFactory.generateEmployee();
        employee.setId(designerUnderTest.getId());

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee oldEmployee = employeeService.edit(employee);
        Employee[] actualEmployees = employeeService.sortByName();

        assertEquals(designerUnderTest, oldEmployee);
        assertTrue(Arrays.asList(actualEmployees).contains(employee));
    }

    @Test
    void testEditWithNotExistingId() {
        Employee employee = employeeFactory.generateEmployee();
        employee.setId(100);

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee oldEmployee = employeeService.edit(employee);
        Employee[] actualEmployees = employeeService.sortByName();

        assertNull(oldEmployee);
        assertFalse(Arrays.asList(actualEmployees).contains(employee));
    }

    @Test
    void testRemove() {
        long id = designerUnderTest.getId();

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee removedEmployee = employeeService.remove(id);
        Employee[] actualEmployees = employeeService.sortByName();

        assertEquals(designerUnderTest, removedEmployee);
        assertFalse(Arrays.asList(actualEmployees).contains(designerUnderTest));
    }

    @Test
    void testRemoveInvalid() {
        long id = -1;

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee[] employeesBeforeRemoval = employeeService.sortByName();
        Employee removedEmployee = employeeService.remove(id);
        Employee[] employeesAfterRemoval = employeeService.sortByName();

        assertNull(removedEmployee);
        assertArrayEquals(employeesBeforeRemoval, employeesAfterRemoval);
    }

    @Test
    void testAdd() {
        Employee employee = employeeFactory.generateEmployee();

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee[] employeesBeforeAdding = employeeService.sortByName();
        employeeService.add(employee);
        Employee[] employeesAfterAdding = employeeService.sortByName();

        assertEquals(employeesBeforeAdding.length + 1, employeesAfterAdding.length);
        assertTrue(Arrays.asList(employeesAfterAdding).contains(employee));
    }

    @Test
    void testAddExisting() {
        Employee existingEmployee = designerUnderTest;

        employeeService = new EmployeeServiceArray(new Employee[]{designerUnderTest, managerUnderTest, developerUnderTest});
        Employee[] employeesBeforeAdding = employeeService.sortByName();
        employeeService.add(existingEmployee);
        Employee[] employeesAfterAdding = employeeService.sortByName();

        assertEquals(employeesBeforeAdding.length, employeesAfterAdding.length);
        assertTrue(Arrays.asList(employeesAfterAdding).contains(existingEmployee));
    }
}
