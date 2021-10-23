package company;

import company.employees.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {

    private EmployeeFactory employeeFactory;

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 100})
    void testGenerateEmployeesOfSize(int expectedSize) {
        employeeFactory = new EmployeeFactory();

        Employee[] employees = employeeFactory.generateEmployees(expectedSize);

        assertEquals(expectedSize, employees.length);
        assertNotNull(employees[0]);
        assertNotNull(employees[expectedSize - 1]);
    }

    @Test
    void testGenerateEmployees() {
        employeeFactory = new EmployeeFactory();

        Employee[] employees = employeeFactory.generateEmployees(1);
        Employee resultEmployee = employees[0];

        assertNotNull(resultEmployee);
        assertNotNull(resultEmployee.getName());
    }

    @Test
    void testGenerateEmployee() {
        employeeFactory = new EmployeeFactory();

        Employee employee = employeeFactory.generateEmployee();

        assertNotNull(employee);
        assertNotNull(employee.getName());
    }

}
