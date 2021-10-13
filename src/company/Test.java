package company;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        EmployeeFactory employeeFactory = new EmployeeFactory();
        EmployeeService employeeService = new EmployeeService(employeeFactory.generateEmployees(12));
        System.out.println("Initially all employees: ");
        employeeService.printEmployees();

        System.out.println("\nTotal sum of money: " + employeeService.calculateSalaryAndBonus());
        System.out.println("\nAdam employees: " + Arrays.toString(employeeService.getByName("Adam")));

        employeeService.sortByName();
        System.out.println("\nSorted by name:");
        employeeService.printEmployees();
        employeeService.sortByNameAndSalary();
        System.out.println("\nSorted by name and salary:");
        employeeService.printEmployees();

        Employee newEmployee = new Employee(1, "Tester", 20, 1000, Gender.MALE, 2);
        employeeService.edit(newEmployee);
        System.out.println("\nAfter adding new employee: ");
        employeeService.printEmployees();

        System.out.println("\nRemoved: " + employeeService.remove(2));
        employeeService.printEmployees();

        System.out.println("\ngetById 2: " + employeeService.getById(2));
        System.out.println("getById 5: " + employeeService.getById(5));
    }
}
