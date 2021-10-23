package company;

import company.employees.Designer;
import company.employees.Employee;
import company.employees.Manager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static company.enums.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    @Test
    void testFinalSalaryManager() {
        double expectedFinalSalary = 10000.0;
        Employee employee = new Manager("ManagerName", 35, (int) expectedFinalSalary, FEMALE);

        double actualFinalSalary = employee.getFinalSalary();

        assertEquals(expectedFinalSalary, actualFinalSalary);
    }

    @Test
    void testFinalSalaryDesigner() {
        double salary = 1000.0;
        int rate = 500;
        int workedDays = 5;
        double expectedFinalSalary = salary + rate * workedDays;
        Employee employee = new Designer("DesignerName", 25, (int) salary, FEMALE, rate, workedDays);

        double actualFinalSalary = employee.getFinalSalary();

        assertEquals(expectedFinalSalary, actualFinalSalary);
    }

    @Test
    @Disabled("Not tested since developer's salary is not predictable according to requirements")
    void testFinalSalaryDeveloper() {

    }

}
