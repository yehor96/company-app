package company.service;

import company.employees.Employee;

public interface EmployeeService {

    void printEmployees();

    double calculateSalaryAndBonus();

    Employee getById(long id);

    Employee[] getByName(String name);

    Employee[] sortByName();

    Employee[] sortByNameAndSalary();

    Employee edit(Employee newEmployee);

    Employee remove(long id);

    void add(Employee newEmployee);
}
