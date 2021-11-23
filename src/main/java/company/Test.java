package company;

import company.employees.Designer;
import company.employees.Employee;
import company.enums.Gender;
import company.service.EmployeeServiceArray;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        EmployeeFactory employeeFactory = new EmployeeFactory();
        EmployeeServiceArray employeeService = new EmployeeServiceArray(employeeFactory.generateEmployees(8));
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

        Employee updateEmployee = new Designer("Tester", 100, 10_000_000, Gender.MALE, 30, 15);
        updateEmployee.setId(2);
        employeeService.edit(updateEmployee);
        System.out.println("\nAfter updating employee with id 2: ");
        employeeService.printEmployees();

        System.out.println("\nRemoved: " + employeeService.remove(2));
        System.out.println("After removing employee:");
        employeeService.printEmployees();

        System.out.println("\ngetById 2: " + employeeService.getById(2));
        System.out.println("getById 5: " + employeeService.getById(5));

        Employee newEmployee = employeeFactory.generateEmployee();
        System.out.println("\nAdd new employee: " + newEmployee.getName());
        employeeService.add(newEmployee);
        employeeService.printEmployees();
    }
}
