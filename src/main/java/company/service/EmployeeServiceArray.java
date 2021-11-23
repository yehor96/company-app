package company.service;

import company.employees.Employee;

public class EmployeeServiceArray implements EmployeeService {

    private Employee[] employees;

    public EmployeeServiceArray(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public void printEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Override
    public double calculateSalaryAndBonus() {
        double totalSalaryAndBonus = 0;
        for (Employee employee : employees) {
            totalSalaryAndBonus += employee.getFinalSalary();
        }
        return totalSalaryAndBonus;
    }

    @Override
    public Employee getById(long id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee[] getByName(String name) {
        int matchingSize = 0;
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                matchingSize++;
            }
        }

        Employee[] resultArray = new Employee[matchingSize];
        if (matchingSize == 0) {
            return resultArray;
        }

        int counter = 0;
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                resultArray[counter++] = employee;
            }
            if (counter == matchingSize) {
                break;
            }
        }
        return resultArray;
    }

    @Override
    public Employee[] sortByName() {
        for (int i = 0; i < employees.length; i++) {
            for (int j = 0; j < employees.length - 1; j++) {
                if (employees[j].getName().compareTo(employees[j + 1].getName()) > 0) {
                    Employee temp = employees[j + 1];
                    employees[j + 1] = employees[j];
                    employees[j] = temp;
                }
            }
        }
        return employees;
    }

    @Override
    public Employee[] sortByNameAndSalary() {
        for (int i = 0; i < employees.length; i++) {
            for (int j = 0; j < employees.length - 1; j++) {
                int compareResult = employees[j].getName().compareTo(employees[j + 1].getName());
                if (compareResult > 0 || compareResult == 0 && employees[j].getSalary() < employees[j + 1].getSalary()) {
                    Employee temp = employees[j + 1];
                    employees[j + 1] = employees[j];
                    employees[j] = temp;
                }
            }
        }
        return employees;
    }

    @Override
    public Employee edit(Employee newEmployee) {
        Employee oldEmployee = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == newEmployee.getId()) {
                oldEmployee = employees[i];
                employees[i] = newEmployee;
                break;
            }
        }

        return oldEmployee;
    }

    @Override
    public Employee remove(long id) {
        Employee removedEmployee = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                removedEmployee = employees[i];
                employees = getNewArrayExcluding(i);
            }
        }
        return removedEmployee;
    }

    @Override
    public void add(Employee newEmployee) {
        if (employeeExists(newEmployee)) {
            return;
        }

        Employee[] newArray = new Employee[employees.length + 1];
        System.arraycopy(employees, 0, newArray, 0, employees.length);
        employees = newArray;
        employees[employees.length - 1] = newEmployee;
    }

    private Employee[] getNewArrayExcluding(int excludingIndex) {
        Employee[] newArray = new Employee[employees.length - 1];
        int counter = 0;
        for (int i = 0; i < employees.length; i++) {
            if (i == excludingIndex) {
                continue;
            }
            newArray[counter++] = employees[i];
        }
        return newArray;
    }

    private boolean employeeExists(Employee employee) {
        for (Employee value : employees) {
            if (value.equals(employee)) {
                return true;
            }
        }
        return false;
    }
}
